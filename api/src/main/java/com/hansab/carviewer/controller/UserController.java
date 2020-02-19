package com.hansab.carviewer.controller;

import com.hansab.carviewer.dto.CarDTO;
import com.hansab.carviewer.dto.UserDTO;
import com.hansab.carviewer.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("{id}/cars")
    public ResponseEntity<List<CarDTO>> findCarsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findCarsByUserId(id));
    }

    @GetMapping(params = {"find", "sort"})
    public ResponseEntity<List<UserDTO>> search(@RequestParam String find, @RequestParam String sort) {
        return ResponseEntity.ok(userService.search(find, sort));
    }
}
