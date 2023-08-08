package com.katas.rpn.service;

import java.util.ArrayList;
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
	
	private Stack getStackById(Long stackId) {
		Optional<Stack> optionalStack = stackRepository.findById(stackId);
		if(optionalStack.isEmpty()) {
			throw new NullPointerException("No stack found, please verify your stackId");
		}
		return optionalStack.get();
	}

	public Optional<Stack> getStack(final Long stackId) {
		return stackRepository.findById(stackId);
	}
	
	/**
	 * Reset the stack
	 * @param stackId: Id of the stack to reset
	 */
	public Stack resetStackValue(Long stackId) throws NullPointerException {
		Stack stack = getStackById(stackId);	
		stack.setStackValue(new ArrayList<Integer>());
		stackRepository.save(stack);
		return stack;
	}

}
