package com.vasu.repository;

import com.vasu.domain.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credentials,Long> {
    Credentials findByName(String name);
}
