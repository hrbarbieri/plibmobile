package com.brightstar.plibmobi.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brightstar.plibmobi.security.model.Users;


@Repository
public interface UsersRepository extends JpaRepository<Users, String>{
	
}
