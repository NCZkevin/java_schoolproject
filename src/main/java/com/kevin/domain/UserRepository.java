package com.kevin.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Created by min on 2017/5/23.
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findAllByVerify(Integer verify);

    User findUserById(Integer id);

    User findByUsername(Long username);


}
