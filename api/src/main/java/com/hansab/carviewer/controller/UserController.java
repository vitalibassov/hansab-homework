package com.hansab.carviewer.controller;

import com.hansab.carviewer.dto.CarDTO;
import com.hansab.carviewer.dto.UserDTO;
import com.hansab.carviewer.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Controller", description = "User specific operations")
@RestController
@RequestMapping("users")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Find all Users", description = "Returns all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll() {
        LOG.info("Request to get all users");
        return ResponseEntity.ok(userService.findAll());
    }

    @Operation(summary = "Find a User by id", description = "Returns a User by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findById(
            @Parameter(description = "User id", required = true)
            @PathVariable Long id) {
        LOG.info("Request to get a user by id={}", id);
        return ResponseEntity.ok(userService.findById(id));
    }

    @Operation(summary = "Find Cars by User's id", description = "Returns Cars by User's id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Cars not found", content = @Content)
    })
    @GetMapping("{id}/cars")
    public ResponseEntity<List<CarDTO>> findCarsByUserId(
            @Parameter(description = "User id", required = true)
            @PathVariable Long id) {
        LOG.info("Request to get cars by user id={}", id);
        return ResponseEntity.ok(userService.findCarsByUserId(id));
    }

    @Operation(summary = "Search for Users", description = "Returns Users by search parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Wrong sort syntax", content = @Content)
    })
    @GetMapping(params = {"find", "sort"})
    public ResponseEntity<List<UserDTO>> search(
            @Parameter(description = "Search text", required = true)
            @RequestParam String find,
            @Parameter(description = "Sort string in field:order format", required = true, example = "name:asc")
            @RequestParam String sort) {
        LOG.info("Request to search users: find={}, sort={}", find, sort);
        return ResponseEntity.ok(userService.search(find, sort));
    }
}
