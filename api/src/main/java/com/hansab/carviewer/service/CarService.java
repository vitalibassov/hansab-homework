package com.hansab.carviewer.service;

import com.hansab.carviewer.dto.CarDTO;
import com.hansab.carviewer.exception.ResourceNotFoundException;
import com.hansab.carviewer.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CarService {
    private static final Logger LOG = LoggerFactory.getLogger(CarService.class);

    private final CarRepository carRepository;
    private final ModelMapper mapper;

    public CarService(CarRepository carRepository, ModelMapper mapper) {
        this.carRepository = carRepository;
        this.mapper = mapper;
    }

    public CarDTO findById(Long id) {
        return carRepository.findById(id)
                .map(car -> mapper.map(car, CarDTO.class))
                .orElseThrow(() -> {
                    LOG.warn("Car with id={} not found", id);
                    return new ResourceNotFoundException("Car", "id", id);
                });
    }

    public List<CarDTO> findAll() {
        return carRepository.findAll()
                .stream()
                .map(car -> mapper.map(car, CarDTO.class))
                .collect(Collectors.toList());
    }
}
