package com.trivia.web.models;

import java.util.List;

public class Trivia {
	
	private Object results;
	private String correct_answer;
	private String question;
	private List incorrect_answers;
	
	
	public Trivia() {}
	
	public Trivia(String question, String correct_answer) {
	    super();
	    this.question = question;
	    this.correct_answer = correct_answer;
	}

	public Object getResults() {
		return results;
	}

	public void setResults(Object results) {
		this.results = results;
	}

	public String getCorrect_answer() {
		return correct_answer;
	}

	public void setCorrect_answer(String correct_answer) {
		this.correct_answer = correct_answer;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	@Override
	public String toString() {
	    return "Trivia [question=" + question + ", correct_answer=" + correct_answer + "]";
	}

	public List getIncorrect_answers() {
		return incorrect_answers;
	}

	public void setIncorrect_answers(List incorrect_answers) {
		this.incorrect_answers = incorrect_answers;
	}
	
}
