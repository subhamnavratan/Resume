package com.Resume.RESUME.model;

public class ProjectDetail {
    private String title;       // project name/title
    private String description; // project description
    private String link;        // project link (GitHub/demo)

    public ProjectDetail() {}

    public ProjectDetail(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;
    }

    // Getters & Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}
