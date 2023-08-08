package com.katas.rpn.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.katas.rpn.repository.StackRepository;
import com.katas.rpn.model.Stack;

import lombok.Data;

@Data
@Service
public class StackService {

	@Autowired
	private StackRepository stackRepository;

	public Optional<Stack> getStack(final Long stackId) {
		return stackRepository.findById(stackId);
	}

}
