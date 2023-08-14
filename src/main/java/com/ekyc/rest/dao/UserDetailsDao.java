package com.ekyc.rest.dao;

import com.ekyc.rest.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsDao extends JpaRepository<UserDetails, Integer> {
}
