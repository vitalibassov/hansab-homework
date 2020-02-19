package com.hansab.carviewer.service;

import com.hansab.carviewer.dto.CarDTO;
import com.hansab.carviewer.dto.UserDTO;
import com.hansab.carviewer.exception.ResourceNotFoundException;
import com.hansab.carviewer.repository.CarRepository;
import com.hansab.carviewer.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final ModelMapper mapper;

    public UserService(UserRepository userRepository, CarRepository carRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.mapper = mapper;
    }

    public UserDTO findById(Long id) {
        return userRepository.findById(id)
                .map(user -> mapper.map(user, UserDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public List<CarDTO> findCarsByUserId(Long id) {
        return carRepository.findAllByUserId(id).stream()
                .map(car -> mapper.map(car, CarDTO.class))
                .collect(Collectors.toList());
    }
}
