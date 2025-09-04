package com.Resume.RESUME.repository;

import com.Resume.RESUME.model.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResumeRepository extends MongoRepository<Resume, String> {

    // Because email is inside ContactDetail
    Optional<Resume> findByContactDetailEmail(String email);

    void deleteByContactDetailEmail(String email);
}
