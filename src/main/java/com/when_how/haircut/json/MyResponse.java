package com.when_how.haircut.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MyResponse {
	
	private int state;
	
	private String content;
	
	public MyResponse() {}
	
	public MyResponse(int state) {
		this.state = state;
	}
	
	public MyResponse(int state, String content) {
		this.state = state;
		this.content = content;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
