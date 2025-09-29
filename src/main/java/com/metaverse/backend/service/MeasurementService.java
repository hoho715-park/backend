package com.metaverse.backend.service;

import com.metaverse.backend.domain.Measurement;
import com.metaverse.backend.domain.User;
import com.metaverse.backend.dto.MeasurementRequest;
import com.metaverse.backend.repository.MeasurementRepository;
import com.metaverse.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final UserRepository userRepository;

    // 저장
    public Measurement saveMeasurement(MeasurementRequest dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Measurement measurement = Measurement.builder()
                .user(user)
                .leftKidney(dto.getLeftKidney())
                .rightKidney(dto.getRightKidney())
                .leftSpleen(dto.getLeftSpleen())
                .rightSpleen(dto.getRightSpleen())
                .leftLung(dto.getLeftLung())
                .rightLung(dto.getRightLung())
                .leftHeart(dto.getLeftHeart())
                .rightHeart(dto.getRightHeart())
                .leftLiver(dto.getLeftLiver())
                .rightLiver(dto.getRightLiver())
                .leftBladder(dto.getLeftBladder())
                .rightBladder(dto.getRightBladder())
                .build();

        return measurementRepository.save(measurement);
    }

    // 조회
    public List<Measurement> getMeasurements(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return measurementRepository.findByUser(user);
    }
}
