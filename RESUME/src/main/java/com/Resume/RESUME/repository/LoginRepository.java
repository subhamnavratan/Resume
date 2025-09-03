package com.Resume.RESUME.repository;

import com.Resume.RESUME.model.Login;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends MongoRepository<Login, String> {
    Login findByEmail(String email);
}

