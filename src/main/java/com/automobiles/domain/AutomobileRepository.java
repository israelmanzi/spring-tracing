package com.automobiles.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AutomobileRepository extends JpaRepository<Automobile, UUID> {
}
