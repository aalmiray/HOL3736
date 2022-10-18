package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    @Query("select t from Todo t where t.description = :description")
    List<Todo> findByDescription(String description);
}