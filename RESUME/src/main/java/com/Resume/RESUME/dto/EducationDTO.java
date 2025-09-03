package com.Resume.RESUME.dto;

public class EducationDTO {
    private String level;        // e.g., "Class 10", "Class 12", "Graduation"
    private String institution;
    private String year;
    private String grade;

    // Getters & Setters
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    public String getInstitution() {
        return institution;
    }
    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }

    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
