package com.katas.rpn.service;

public enum Operation {
	add {
		public Integer apply(Integer a, Integer b) { return a + b; }
	},
	substract {
		public Integer apply(Integer a, Integer b) { return a - b; }
	},
	multiply {
		public Integer apply(Integer a, Integer b) { return a * b; }
	},
	divide {
		public Integer apply(Integer a, Integer b) { return a / b; }
	},
	;

	public abstract Integer apply(Integer a, Integer b);
}
