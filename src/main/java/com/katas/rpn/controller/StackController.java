package com.katas.rpn.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katas.rpn.model.Stack;
import com.katas.rpn.service.StackService;

@RestController
@RequestMapping("/rpn")
public class StackController {

	@Autowired
	private StackService stackService;

	/**
	 * Get stack by stack_id
	 * @param id
	 * @return - null if no Stack match the passing id
	 * 		   - The matched Stack object
	 */
	@GetMapping("/stack/{id}")
	public Optional<Stack> getStackById(@PathVariable Long id) {
		return stackService.getStack(id);
	}

}