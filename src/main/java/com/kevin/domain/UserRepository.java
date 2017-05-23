package com.kevin.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

/**
 * Created by min on 2017/5/23.
 */
public interface UserRepository extends JpaRepository<User,Integer> {

//    @Modifying
//    @Query("select u from User where verify = -1")
    List<User> findAllByVerify(Integer verify);

    @Modifying
    @Query("update User u set u.lastLoginTime = current_date where u.id = ?1")
    Date modifyLoginDate(int id);

    @Modifying
    @Query("update User u set u.loginCount=u.loginCount+1 where u.id = ?1")
    int updateLoginCount(int id);

}
