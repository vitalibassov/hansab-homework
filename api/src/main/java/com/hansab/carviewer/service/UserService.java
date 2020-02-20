package com.hansab.carviewer.service;

import com.hansab.carviewer.dto.CarDTO;
import com.hansab.carviewer.dto.UserDTO;
import com.hansab.carviewer.exception.ResourceNotFoundException;
import com.hansab.carviewer.repository.CarRepository;
import com.hansab.carviewer.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
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
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return carRepository.findAllByUserId(id).stream()
                .map(car -> mapper.map(car, CarDTO.class))
                .collect(Collectors.toList());
    }

    public List<UserDTO> search(String find, String sort) {
        String[] sortParams = sort.split(":");
        if (sortParams.length < 2) {
            throw new IllegalArgumentException("Wrong sort param. Should be in \"fieldname:order\" format");
        }

        Sort.Direction direction = Sort.Direction.fromString(sortParams[1].toUpperCase());
        String property = sortParams[0];

        return userRepository.findAllByNameContainingIgnoreCase(find, Sort.by(direction, property))
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}
