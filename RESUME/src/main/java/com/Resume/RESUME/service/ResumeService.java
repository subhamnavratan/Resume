package com.Resume.RESUME.service;

import com.Resume.RESUME.dto.*;
import com.Resume.RESUME.model.ContactDetail;
import com.Resume.RESUME.model.EducationDetail;
import com.Resume.RESUME.model.ProjectDetail;
import com.Resume.RESUME.model.Resume;
import com.Resume.RESUME.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    // ---------- CREATE OR UPDATE RESUME ----------
    public Resume saveResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    // ---------- GET ALL RESUMES ----------
    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    // ---------- GET RESUME BY EMAIL ----------
    public Optional<Resume> getResumeByEmail(String email) {
        return resumeRepository.findByEmail(email);
    }

    // ---------- DELETE RESUME ----------
    public void deleteResume(String email) {
        resumeRepository.deleteByEmail(email);
    }

    // ---------- CONTACT DETAILS ----------
    public ContactDetail getContactDetail(String email) {
        return resumeRepository.findByEmail(email)
                .map(Resume::getContactDetail)
                .orElse(null);
    }

    public ContactDetail updateContact(String email, ContactDTO dto) {
        Resume resume = resumeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        ContactDetail contact = resume.getContactDetail();
        if (contact == null) contact = new ContactDetail();

        contact.setEmail(dto.getEmail());
        contact.setPhoneNumber(dto.getPhoneNumber());
        contact.setLinkedin(dto.getLinkedin());
        contact.setGithub(dto.getGithub());
        contact.setAddress(dto.getAddress());

        resume.setContactDetail(contact);
        resumeRepository.save(resume);
        return contact;
    }

    // ---------- EDUCATION DETAILS ----------
    public List<EducationDetail> getEducationDetails(String email) {
        return resumeRepository.findByEmail(email)
                .map(Resume::getEducationDetails)
                .orElse(null);
    }

    public List<EducationDetail> updateEducation(String email, List<EducationDTO> dtos) {
        Resume resume = resumeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        List<EducationDetail> details = dtos.stream().map(dto -> {
            EducationDetail edu = new EducationDetail();
            edu.setLevel(dto.getLevel());
            edu.setInstitution(dto.getInstitution());
            edu.setYear(dto.getYear());
            edu.setGrade(dto.getGrade());
            return edu;
        }).collect(Collectors.toList());

        resume.setEducationDetails(details);
        resumeRepository.save(resume);
        return details;
    }

    // ---------- SKILLS ----------
    public List<String> getSkills(String email) {
        return resumeRepository.findByEmail(email)
                .map(Resume::getSkills)
                .orElse(null);
    }

    public List<String> updateSkills(String email, SkillsDTO dto) {
        Resume resume = resumeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        resume.setSkills(dto.getSkills());
        resumeRepository.save(resume);

        return resume.getSkills();
    }

    // ---------- PROJECTS ----------
    public List<ProjectDetail> getProjects(String email) {
        return resumeRepository.findByEmail(email)
                .map(Resume::getProjects)
                .orElse(null);
    }

    public List<ProjectDetail> updateProjects(String email, List<ProjectDTO> dtos) {
        Resume resume = resumeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        List<ProjectDetail> projects = dtos.stream().map(dto -> {
            ProjectDetail project = new ProjectDetail();
            project.setTitle(dto.getTitle());
            project.setDescription(dto.getDescription());
            project.setLink(dto.getLink());
            return project;
        }).collect(Collectors.toList());

        resume.setProjects(projects);
        resumeRepository.save(resume);

        return projects;
    }

    // ---------- ACHIEVEMENT ----------
    public String getAchievement(String email) {
        return resumeRepository.findByEmail(email)
                .map(Resume::getAchievement)
                .orElse(null);
    }

    public String updateAchievement(String email, AchievementDTO dto) {
        Resume resume = resumeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        resume.setAchievement(dto.getAchievement());
        resumeRepository.save(resume);

        return resume.getAchievement();
    }

    // ---------- ABOUT ----------
    public String getAbout(String email) {
        return resumeRepository.findByEmail(email)
                .map(Resume::getAbout)
                .orElse(null);
    }

    public String updateAbout(String email, AboutDTO dto) {
        Resume resume = resumeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        resume.setAbout(dto.getAbout());
        resumeRepository.save(resume);

        return resume.getAbout();
    }
}
