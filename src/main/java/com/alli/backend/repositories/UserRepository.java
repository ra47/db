package com.alli.backend.repositories;

import com.alli.backend.models.User;
import com.alli.backend.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findByCitizenId(String citizenId);

    Optional<User> findByUsername(String username);

    List<User> findByRole(Role role);
}
