package com.gcc.taotaopiao.entity;

import java.util.List;

public class Trains5 {
	public Trains5() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Trains5(Result result) {
		super();
		this.result = result;
	}

	@Override
	public String toString() {
		return "Trains [result=" + result + "]";
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	private Result result;

	public  static class Result {

		public Result() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Result(List<A> list) {
			super();
			this.list = list;
		}

		@Override
		public String toString() {
			return "Result [list=" + list + "]";
		}

		public List<A> getList() {
			return list;
		}

		public void setList(List<A> list) {
			this.list = list;
		}

		private List<A> list;

	}

}