package com.kookietalk.kt.entity;

import com.kookietalk.kt.model.User;

public class Instructor extends User {
	private Double ranking;

	public Double getRanking() {
		return ranking;
	}

	public void setRanking(Double ranking) {
		this.ranking = ranking;
	}
	
}
