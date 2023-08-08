package com.katas.rpn.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "stack")
public class Stack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stackId;

	private List<Integer> stackValue;

}