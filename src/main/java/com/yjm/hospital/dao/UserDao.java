package com.yjm.hospital.dao;

import com.yjm.hospital.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface UserDao extends JpaRepository<User, Long> {

    public User findByUserid(Long userid);

    @Modifying
    @Query(value = "update User set blnisvoid = 1,createby=?2, createtime=?3 where userid=?1", nativeQuery = true)
    public void deleteByUserid(Long id, Long userid, Date date);

    public void deleteByUserid(Long userid);

}