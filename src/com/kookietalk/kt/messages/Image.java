package com.kookietalk.kt.messages;

public class Image extends Message {
	@Override
	public String toString() {
		return "Image [title=" + title + ", chapter=" + chapter + ", lesson=" + lesson + ", slide=" + slide + "]";
	}
	private int title;
	private int chapter;
	private int lesson;
	private int slide;
	
	public int getLesson() {
		return lesson;
	}
	public void setLesson(int lesson) {
		this.lesson = lesson;
	}
	public int getSlide() {
		return slide;
	}
	public void setSlide(int slide) {
		this.slide = slide;
	}
	public int getTitle() {
		return title;
	}
	public void setTitle(int title) {
		this.title = title;
	}
	public int getChapter() {
		return chapter;
	}
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}
}
