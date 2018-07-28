package com.tcf.data.entity;

import java.util.Map;

/**
 * @author Archer Tan
 */
public class Question {
    private String grade;
    private String subject;
    private String author;
    private String questionType;
    private String questionTitle;
    private Map<String,String> options;
    private String answer;
    private String answerDetail;
    private String difficulty;

    public Question() {
    }

    public Question(String grade, String subject, String author, String questionType, String questionTitle, Map<String, String> options, String answer, String answerDetail, String difficulty) {
        this.grade = grade;
        this.subject = subject;
        this.author = author;
        this.questionType = questionType;
        this.questionTitle = questionTitle;
        this.options = options;
        this.answer = answer;
        this.answerDetail = answerDetail;
        this.difficulty = difficulty;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerDetail() {
        return answerDetail;
    }

    public void setAnswerDetail(String answerDetail) {
        this.answerDetail = answerDetail;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
