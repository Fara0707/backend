package com.nikola.backend1.repos;

import com.nikola.backend1.domain.UniversityGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface UniversityGroupRepo extends CrudRepository<UniversityGroup, Integer> {
    UniversityGroup findByGroupName(@Param("groupName") String groupName);
}
