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

    // ---------- GET RESUME BY ID ----------
    @GetMapping("/{id}")
    public ResponseEntity<Resume> getResumeById(@PathVariable String id) {
        return resumeService.getResumeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------- DELETE RESUME ----------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResume(@PathVariable String id) {
        resumeService.deleteResume(id);
        return ResponseEntity.ok("Resume deleted with ID: " + id);
    }

    // ---------- CONTACT DETAILS ----------
    @GetMapping("/{id}/contact")
    public ResponseEntity<ContactDetail> getContactDetail(@PathVariable String id) {
        return ResponseEntity.ok(resumeService.getContactDetail(id));
    }

    @PutMapping("/{id}/contact")
    public ResponseEntity<ContactDetail> updateContactDetail(@PathVariable String id, @RequestBody ContactDTO dto) {
        return ResponseEntity.ok(resumeService.updateContact(id, dto));
    }

    // ---------- EDUCATION DETAILS ----------
    @GetMapping("/{id}/education")
    public ResponseEntity<List<EducationDetail>> getEducationDetails(@PathVariable String id) {
        return ResponseEntity.ok(resumeService.getEducationDetails(id));
    }

    @PutMapping("/{id}/education")
    public ResponseEntity<List<EducationDetail>> updateEducationDetails(@PathVariable String id,
                                                                        @RequestBody List<EducationDTO> dtos) {
        return ResponseEntity.ok(resumeService.updateEducation(id, dtos));
    }

    // ---------- SKILLS ----------
    @GetMapping("/{id}/skills")
    public ResponseEntity<List<String>> getSkills(@PathVariable String id) {
        return ResponseEntity.ok(resumeService.getSkills(id));
    }

    @PutMapping("/{id}/skills")
    public ResponseEntity<List<String>> updateSkills(@PathVariable String id, @RequestBody SkillsDTO dto) {
        return ResponseEntity.ok(resumeService.updateSkills(id, dto));
    }
    // ---------- PROJECTS ----------
    @GetMapping("/{id}/projects")
    public ResponseEntity<List<ProjectDetail>> getProjects(@PathVariable String id) {
        return ResponseEntity.ok(resumeService.getProjects(id));
    }

    @PutMapping("/{id}/projects")
    public ResponseEntity<List<ProjectDetail>> updateProjects(@PathVariable String id, @RequestBody List<ProjectDTO> dtos) {
        return ResponseEntity.ok(resumeService.updateProjects(id, dtos));
    }



    // ---------- ACHIEVEMENT ----------
    @GetMapping("/{id}/achievement")
    public ResponseEntity<String> getAchievement(@PathVariable String id) {
        return ResponseEntity.ok(resumeService.getAchievement(id));
    }

    @PutMapping("/{id}/achievement")
    public ResponseEntity<String> updateAchievement(@PathVariable String id, @RequestBody AchievementDTO dto) {
        return ResponseEntity.ok(resumeService.updateAchievement(id, dto));
    }

    // ---------- ABOUT ----------
    @GetMapping("/{id}/about")
    public ResponseEntity<String> getAbout(@PathVariable String id) {
        return ResponseEntity.ok(resumeService.getAbout(id));
    }

    @PutMapping("/{id}/about")
    public ResponseEntity<String> updateAbout(@PathVariable String id, @RequestBody AboutDTO dto) {
        return ResponseEntity.ok(resumeService.updateAbout(id, dto));
    }
}


