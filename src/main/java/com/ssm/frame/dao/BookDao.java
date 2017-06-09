package com.ssm.frame.dao;

import com.ssm.frame.entity.Book;
import com.ssm.frame.entity.BookExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookDao {
    int countByExample(BookExample example);

    int deleteByExample(BookExample example);

    int deleteByPrimaryKey(Long bookId);

    int insert(Book record);

    int insertSelective(Book record);

    List<Book> selectByExample(BookExample example);

    Book selectByPrimaryKey(Long bookId);

    int updateByExampleSelective(@Param("record") Book record, @Param("example") BookExample example);

    int updateByExample(@Param("record") Book record, @Param("example") BookExample example);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> selectBookList();

    int reduceNumber(long bookId);
}