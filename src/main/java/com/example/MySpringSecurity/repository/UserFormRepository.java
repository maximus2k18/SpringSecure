package com.example.MySpringSecurity.repository;

import com.example.MySpringSecurity.model.UserForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFormRepository extends JpaRepository<UserForm, Long> {

}
