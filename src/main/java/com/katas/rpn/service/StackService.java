package com.katas.rpn.service;

import java.util.ArrayList;
import java.util.List;
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
	
	private Stack updateStackValue(Stack stack, List<Integer> values) {
		stack.setStackValue(values);
		stackRepository.save(stack);
		return stack;
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
		return updateStackValue(stack, new ArrayList<Integer>());
	}
	
	/**
	 * Push new value to the stack
	 * @param stackId
	 * @param value
	 * @return
	 * @throws NullPointerException
	 */
	public Stack pushStackValue(Long stackId, Integer value) throws NullPointerException {
		Stack stack = getStackById(stackId);
		List<Integer> newValues = stack.getStackValue();
		newValues.add(value);
		return updateStackValue(stack, newValues);
	}

}
