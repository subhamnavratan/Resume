package com.Resume.RESUME.controller;

import com.Resume.RESUME.dto.*;
import com.Resume.RESUME.model.ContactDetail;
import com.Resume.RESUME.model.EducationDetail;
import com.Resume.RESUME.model.ProjectDetail;
import com.Resume.RESUME.model.Resume;
import com.Resume.RESUME.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    // ---------- CREATE RESUME ----------
    @PostMapping
    public ResponseEntity<Resume> createResume(@RequestBody Resume resume) {
        return ResponseEntity.ok(resumeService.saveResume(resume));
    }

    // ---------- GET ALL RESUMES ----------
    @GetMapping
    public ResponseEntity<List<Resume>> getAllResumes() {
        return ResponseEntity.ok(resumeService.getAllResumes());
    }

    // ---------- GET RESUME BY EMAIL ----------
    @GetMapping("/{email}")
    public ResponseEntity<Resume> getResumeByEmail(@PathVariable String email) {
        return resumeService.getResumeByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------- DELETE RESUME ----------
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteResume(@PathVariable String email) {
        resumeService.deleteResume(email);
        return ResponseEntity.ok("Resume deleted with Email: " + email);
    }

    // ---------- CONTACT DETAILS ----------
    @GetMapping("/{email}/contact")
    public ResponseEntity<ContactDetail> getContactDetail(@PathVariable String email) {
        return ResponseEntity.ok(resumeService.getContactDetail(email));
    }

    @PutMapping("/{email}/contact")
    public ResponseEntity<ContactDetail> updateContactDetail(@PathVariable String email, @RequestBody ContactDTO dto) {
        return ResponseEntity.ok(resumeService.updateContact(email, dto));
    }

    // ---------- EDUCATION DETAILS ----------
    @GetMapping("/{email}/education")
    public ResponseEntity<List<EducationDetail>> getEducationDetails(@PathVariable String email) {
        return ResponseEntity.ok(resumeService.getEducationDetails(email));
    }

    @PutMapping("/{email}/education")
    public ResponseEntity<List<EducationDetail>> updateEducationDetails(@PathVariable String email,
                                                                        @RequestBody List<EducationDTO> dtos) {
        return ResponseEntity.ok(resumeService.updateEducation(email, dtos));
    }

    // ---------- SKILLS ----------
    @GetMapping("/{email}/skills")
    public ResponseEntity<List<String>> getSkills(@PathVariable String email) {
        return ResponseEntity.ok(resumeService.getSkills(email));
    }

    @PutMapping("/{email}/skills")
    public ResponseEntity<List<String>> updateSkills(@PathVariable String email, @RequestBody SkillsDTO dto) {
        return ResponseEntity.ok(resumeService.updateSkills(email, dto));
    }

    // ---------- PROJECTS ----------
    @GetMapping("/{email}/projects")
    public ResponseEntity<List<ProjectDetail>> getProjects(@PathVariable String email) {
        return ResponseEntity.ok(resumeService.getProjects(email));
    }

    @PutMapping("/{email}/projects")
    public ResponseEntity<List<ProjectDetail>> updateProjects(@PathVariable String email, @RequestBody List<ProjectDTO> dtos) {
        return ResponseEntity.ok(resumeService.updateProjects(email, dtos));
    }

    // ---------- ACHIEVEMENT ----------
    @GetMapping("/{email}/achievement")
    public ResponseEntity<String> getAchievement(@PathVariable String email) {
        return ResponseEntity.ok(resumeService.getAchievement(email));
    }

    @PutMapping("/{email}/achievement")
    public ResponseEntity<String> updateAchievement(@PathVariable String email, @RequestBody AchievementDTO dto) {
        return ResponseEntity.ok(resumeService.updateAchievement(email, dto));
    }

    // ---------- ABOUT ----------
    @GetMapping("/{email}/about")
    public ResponseEntity<String> getAbout(@PathVariable String email) {
        return ResponseEntity.ok(resumeService.getAbout(email));
    }

    @PutMapping("/{email}/about")
    public ResponseEntity<String> updateAbout(@PathVariable String email, @RequestBody AboutDTO dto) {
        return ResponseEntity.ok(resumeService.updateAbout(email, dto));
    }
}


