package com.hansab.carviewer.service;

import com.hansab.carviewer.dto.CarDTO;
import com.hansab.carviewer.exception.ResourceNotFoundException;
import com.hansab.carviewer.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final ModelMapper mapper;

    public CarService(CarRepository carRepository, ModelMapper mapper) {
        this.carRepository = carRepository;
        this.mapper = mapper;
    }

    public CarDTO findById(Long id) {
        return carRepository.findById(id)
                .map(car -> mapper.map(car, CarDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Car", "id", id));
    }

    public List<CarDTO> findAll() {
        return carRepository.findAll()
                .stream()
                .map(car -> mapper.map(car, CarDTO.class))
                .collect(Collectors.toList());
    }
}
