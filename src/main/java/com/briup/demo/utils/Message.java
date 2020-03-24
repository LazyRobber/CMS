package com.briup.demo.utils;

/**
 * 自定义相应返回类型
 * @author wangkang
 */
public class Message<T> {
	
	/**
	 * 状态码
	 */
	private Integer status;
	
	/**
	 * 返回信息
	 */
	private String message;
	
	/**
	 * 返回的数据类型
	 */
	private T date;
	
	/**
	 * 返回的时间
	 */
	private Long time;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getDate() {
		return date;
	}

	public void setDate(T date) {
		this.date = date;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(Integer status, String message, T date, Long time) {
		super();
		this.status = status;
		this.message = message;
		this.date = date;
		this.time = time;
	}
	
	
}
