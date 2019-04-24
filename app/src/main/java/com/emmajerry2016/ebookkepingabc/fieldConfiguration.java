package com.emmajerry2016.ebookkepingabc;

public class fieldConfiguration {

    private int serialNumber;
    private String Title;
    private String Author;
    private String Comment;
    private String Content;

    public fieldConfiguration(int serialNumber, String title, String author, String comment, String content) {
        this.serialNumber = serialNumber;
        Title = title;
        Author = author;
        Comment = comment;
        Content = content;
    }

    public fieldConfiguration() {

    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public String toString() {
        return "fieldConfiguration{" +
                "serialNumber=" + serialNumber +
                ", Title='" + Title + '\'' +
                ", Author='" + Author + '\'' +
                ", Comment='" + Comment + '\'' +
                ", Content='" + Content + '\'' +
                '}';
    }
}
