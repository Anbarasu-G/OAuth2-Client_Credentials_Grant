package com.techtez.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techtez.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	Optional<Client> findByClientId(String cilentId);
}
