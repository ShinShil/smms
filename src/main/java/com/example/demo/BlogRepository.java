package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<BlogModel, Long> {
}
