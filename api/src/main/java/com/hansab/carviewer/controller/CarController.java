package com.hansab.carviewer.controller;

import com.hansab.carviewer.dto.CarDTO;
import com.hansab.carviewer.service.CarService;
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


@Tag(name = "Car Controller", description = "Car specific operations")
@RestController
@RequestMapping("cars")
public class CarController {
    private static final Logger LOG = LoggerFactory.getLogger(CarController.class);

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Operation(summary = "Find all Cars", description = "Returns all cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping()
    public ResponseEntity<List<CarDTO>> findAll() {
        LOG.info("Request to get all cars");
        return ResponseEntity.ok(carService.findAll());
    }

    @Operation(summary = "Find a Car by id", description = "Returns a Car by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Car not found", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<CarDTO> findById(
            @Parameter(description = "Car id", required = true)
            @PathVariable Long id) {
        LOG.info("Request to get a car by id={}", id);
        return ResponseEntity.ok(carService.findById(id));
    }
}
