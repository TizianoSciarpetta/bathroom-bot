package com.telegram.poopbot.repository;

import com.telegram.poopbot.model.PoopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoopUserRepository extends JpaRepository<PoopUser, Long> {
    boolean existsByIdAndFirstNameAndLastNameAndUserName(long id, String firstName, String lastName, String username);
}