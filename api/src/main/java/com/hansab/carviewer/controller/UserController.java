package com.hansab.carviewer.controller;

import com.hansab.carviewer.dto.CarDTO;
import com.hansab.carviewer.dto.UserDTO;
import com.hansab.carviewer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll() {
        LOG.info("Request to get all users");
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        LOG.info("Request to get a user by id={}", id);
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("{id}/cars")
    public ResponseEntity<List<CarDTO>> findCarsByUserId(@PathVariable Long id) {
        LOG.info("Request to get cars by user id={}", id);
        return ResponseEntity.ok(userService.findCarsByUserId(id));
    }

    @GetMapping(params = {"find", "sort"})
    public ResponseEntity<List<UserDTO>> search(@RequestParam String find, @RequestParam String sort) {
        LOG.info("Request to search users: find={}, sort={}", find, sort);
        return ResponseEntity.ok(userService.search(find, sort));
    }
}
