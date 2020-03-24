package com.briup.demo.utils;
/*
 * 自定义异常
 * @author wk
 */
public class CustomerException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 	状态码
	 */
	private Integer code;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * 
	 * @param code 异常编码
	 * @param message 异常信息
	 */
	public CustomerException(Integer code,String message) {
		super(message);
		this.code=code;
	}
}
