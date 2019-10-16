package com.calibre.rms.fx.model;

import java.util.List;

public class Mail {

    private String from;
    private List<String> to;
    private String subject;
    private String content;
    private String path;
    private String fileName;

    public Mail() {
    }

    public Mail(String from, List<String> to, String subject, String content,String path,String fileName) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.path = path;
        this.fileName = fileName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public String getPath() {
        return path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}