package com.wisely.domain;

public class WiselyResponse {
	private String responseMessage;
	public WiselyResponse(String responseMessage){
		this.responseMessage=responseMessage;
	}
	public String getResponseMessage(){
		return responseMessage;
	}

}
