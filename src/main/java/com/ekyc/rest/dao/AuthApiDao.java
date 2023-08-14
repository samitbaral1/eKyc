package com.ekyc.rest.dao;

import com.ekyc.rest.entity.AuthResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthApiDao extends JpaRepository<AuthResponse, Integer> {
    public AuthResponse findAuthResponseById(String id);
}
