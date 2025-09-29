package com.metaverse.backend.controller;

import com.metaverse.backend.domain.Measurement;
import com.metaverse.backend.dto.MeasurementRequest;
import com.metaverse.backend.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/measurements")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // ✅ React 주소에서만 접근 허용
public class MeasurementController {

    private final MeasurementService measurementService;

    // 저장
    @PostMapping
    public Measurement createMeasurement(@RequestBody MeasurementRequest request) {
        return measurementService.saveMeasurement(request);
    }

    // 특정 사용자(userId)의 모든 기록 조회
    @GetMapping("/{userId}")
    public List<Measurement> getMeasurements(@PathVariable Long userId) {
        return measurementService.getMeasurements(userId);
    }
}
