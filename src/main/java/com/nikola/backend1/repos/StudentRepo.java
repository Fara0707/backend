package com.nikola.backend1.repos;

import com.nikola.backend1.domain.Student;
import org.springframework.data.repository.CrudRepository;


public interface StudentRepo extends CrudRepository<Student, Integer> {
}
