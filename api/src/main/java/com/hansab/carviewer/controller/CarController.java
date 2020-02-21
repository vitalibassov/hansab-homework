package com.hansab.carviewer.controller;

import com.hansab.carviewer.dto.CarDTO;
import com.hansab.carviewer.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cars")
public class CarController {
    private static final Logger LOG = LoggerFactory.getLogger(CarController.class);

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public ResponseEntity<List<CarDTO>> findAll() {
        LOG.info("Request to get all cars");
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<CarDTO> findById(@PathVariable Long id) {
        LOG.info("Request to get a car by id={}", id);
        return ResponseEntity.ok(carService.findById(id));
    }
}
