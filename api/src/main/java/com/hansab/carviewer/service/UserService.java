package com.hansab.carviewer.service;

import com.hansab.carviewer.dto.CarDTO;
import com.hansab.carviewer.dto.UserDTO;
import com.hansab.carviewer.exception.ResourceNotFoundException;
import com.hansab.carviewer.exception.WrongSortSyntaxException;
import com.hansab.carviewer.repository.CarRepository;
import com.hansab.carviewer.repository.UserRepository;
import com.hansab.carviewer.utils.SortUtility;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(CarService.class);

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
                .orElseThrow(() -> {
                    LOG.warn("User with id={} not found", id);
                    return new ResourceNotFoundException("User", "id", id);
                });
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public List<CarDTO> findCarsByUserId(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> {
                    LOG.warn("User with id={} not found", id);
                    return new ResourceNotFoundException("User", "id", id);
                });
        return carRepository.findAllByUserId(id).stream()
                .map(car -> mapper.map(car, CarDTO.class))
                .collect(Collectors.toList());
    }

    public List<UserDTO> search(String find, String sort) {
        return userRepository.findAllByNameContainingIgnoreCase(find, SortUtility.generateSort(sort))
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}
