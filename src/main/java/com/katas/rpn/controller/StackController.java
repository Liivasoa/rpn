package com.katas.rpn.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/**
	 * Reset the stack value
	 * @param id
	 * @return
	 */
	@PutMapping("/stack/{id}")
	public ResponseEntity<Object> resetStack(@PathVariable Long id) {
		try {
			var stack = stackService.resetStackValue(id);
			return ResponseEntity.ok(stack);
		} catch (NullPointerException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	/**
	 * Push new value to the stack
	 * @param id
	 * @param value
	 * @return
	 */
	@PatchMapping("/stack/{id}")
	public ResponseEntity<Object> pushValue(@PathVariable Long id, @RequestParam Integer value) {
		try {
			var stack = stackService.pushStackValue(id, value);
			return ResponseEntity.ok(stack);
		} catch (NullPointerException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}

}