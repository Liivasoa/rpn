package com.katas.rpn.repository;

import org.springframework.data.repository.CrudRepository;

import com.katas.rpn.model.Stack;

public interface StackRepository extends CrudRepository<Stack, Long> {

}
