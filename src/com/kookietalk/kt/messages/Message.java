package com.kookietalk.kt.messages;

public class Message {
	@Override
	public String toString() {
		return "Message [type=" + type + "]";
	}

	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
