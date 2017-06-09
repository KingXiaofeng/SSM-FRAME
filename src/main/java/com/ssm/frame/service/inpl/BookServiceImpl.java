package com.ssm.frame.service.inpl;

import com.ssm.frame.dao.AppointmentDao;
import com.ssm.frame.dao.BookDao;
import com.ssm.frame.dto.AppointExecution;
import com.ssm.frame.entity.Appointment;
import com.ssm.frame.entity.Book;
import com.ssm.frame.entity.BookExample;
import com.ssm.frame.enums.AppointStateEnum;
import com.ssm.frame.exception.NoNumberException;
import com.ssm.frame.exception.RepeatAppointException;
import com.ssm.frame.service.interfaces.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务接口：站在"使用者"角度设计接口 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
 */
@Service
public class BookServiceImpl implements IBookService{

    private final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    BookDao bookDao;
    @Autowired
    AppointmentDao appointmentDao;

    /**
     * 查询一本图书
     *
     * @param bookId
     * @return
     */
    @Override
    public Book getById(long bookId) {
        return bookDao.selectByPrimaryKey(bookId);
    }

    /**
     * 查询所有图书
     *
     * @return
     */
    @Override
    public List<Book> getList() {
        return bookDao.selectBookList();
    }

    /**
     * 预约图书
     *
     * 使用注解控制事务方法的优点：
     * 1.开发团队达成一致约定，明确标注事务方法的编程风格
     * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
     * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
     *
     * @param bookId
     * @param studentId
     * @return
     */
    @Override
    @Transactional
    public AppointExecution appoint(long bookId, long studentId) {

        // 进行减库存操作，不需要查出来，直接更新，若更新成功继续其他操作，若不成功，抛出异常
        int update = bookDao.reduceNumber(bookId);

        if (update < 1) {
            throw new NoNumberException("库存不足");
        } else {
            // 执行预约操作
            Appointment appointmentEntity = new Appointment();
            appointmentEntity.setStudentId(studentId);
            appointmentEntity.setBookId(bookId);

            int insert = appointmentDao.insert(appointmentEntity);

            if (insert < 1) {
                throw new RepeatAppointException("重复预约");
            } else {
                // 预约成功
                // 使用联合主键，以保证每种书能被多个用户订阅，同时，一个同学也可以订阅多种书籍
                Appointment entity = appointmentDao.selectByPrimaryKey(bookId, studentId);
                return new AppointExecution(bookId, AppointStateEnum.SUCCESS, entity);
            }
        }
    }
}
