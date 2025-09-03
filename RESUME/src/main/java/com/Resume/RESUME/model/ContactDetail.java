package com.Resume.RESUME.model;
public class ContactDetail {
    private String email;           // Email address
    private String phoneNumber;     // Phone number
    private String linkedin;        // LinkedIn profile link
    private String github;          // GitHub profile link
    private String address;         // Physical address


    // Getters & Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getLinkedin() { return linkedin; }
    public void setLinkedin(String linkedin) { this.linkedin = linkedin; }

    public String getGithub() { return github; }
    public void setGithub(String github) { this.github = github; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

}
