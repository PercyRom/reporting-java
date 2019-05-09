package com.tracking.guia.remision.bean;

import java.io.Serializable;

public class BeanMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean sucess;
	private String msg;
	private Integer code;
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public boolean isSucess() {
		return sucess;
	}

	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}

	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
