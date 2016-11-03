package com.ly.xinli001.action;

import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport{
	private String hello;

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}

	@Override
	public String execute() throws Exception {
		System.out.println("HelloAction.execute()"+hello);
		return SUCCESS;
	}
	

}
