package com.kevin.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by min on 2017/5/25.
 */
public interface GroupRepository extends JpaRepository<CGroup,Integer> {
    CGroup findByGroupName(String groupName);
}
