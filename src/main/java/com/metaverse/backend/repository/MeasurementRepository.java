package com.metaverse.backend.repository;

import com.metaverse.backend.domain.Measurement;
import com.metaverse.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    List<Measurement> findByUser(User user);
}
