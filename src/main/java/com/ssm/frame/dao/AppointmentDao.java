package com.ssm.frame.dao;

import com.ssm.frame.entity.Appointment;
import com.ssm.frame.entity.AppointmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppointmentDao {
    int countByExample(AppointmentExample example);

    int deleteByExample(AppointmentExample example);

    int deleteByPrimaryKey(@Param("bookId") Long bookId, @Param("studentId") Long studentId);

    int insert(Appointment record);

    int insertSelective(Appointment record);

    List<Appointment> selectByExample(AppointmentExample example);

    Appointment selectByPrimaryKey(@Param("bookId") Long bookId, @Param("studentId") Long studentId);

    int updateByExampleSelective(@Param("record") Appointment record, @Param("example") AppointmentExample example);

    int updateByExample(@Param("record") Appointment record, @Param("example") AppointmentExample example);

    int updateByPrimaryKeySelective(Appointment record);

    int updateByPrimaryKey(Appointment record);
}