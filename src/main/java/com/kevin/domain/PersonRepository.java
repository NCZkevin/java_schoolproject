package com.kevin.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by kevin on 17/4/22.
 */
public interface PersonRepository extends JpaRepository<Person,Integer> {
    Person findByStudentNum(Long studentNum);

    @Query(value="select * from person p where p.name like CONCAT('%',?1,'%') OR p.age like CONCAT('%',?1,'%') OR p.city like CONCAT('%',?1,'%') OR " +
            "p.classes like CONCAT('%',?1,'%') OR p.email like CONCAT('%',?1,'%') OR p.enter_year like CONCAT('%',?1,'%') OR p.graduation_year like CONCAT('%',?1,'%')" +
            "OR p.major like CONCAT('%',?1,'%') OR p.phone_number like CONCAT('%',?1,'%') OR p.sex like CONCAT('%',?1,'%') OR p.student_num like CONCAT('%',?1,'%')" +
            "OR p.work_unit like CONCAT('%',?1,'%')",nativeQuery=true)
    List<Person> findByAttribute(@Param("name") String name);
}
