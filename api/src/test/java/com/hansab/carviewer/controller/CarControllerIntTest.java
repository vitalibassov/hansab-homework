package com.hansab.carviewer.controller;

import com.hansab.carviewer.CarviewerApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = CarviewerApplication.class)
public class CarControllerIntTest {


    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldFindAll() throws Exception {
        mvc.perform(get("/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].make").value("Lada"))
                .andExpect(jsonPath("$[0].model").value("2101"))
                .andExpect(jsonPath("$[0].numberplate").value("123ASD"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].make").value("Kia"))
                .andExpect(jsonPath("$[1].model").value("Sorento"))
                .andExpect(jsonPath("$[1].numberplate").value("534TTT"))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].make").value("Skoda"))
                .andExpect(jsonPath("$[2].model").value("Octavia"))
                .andExpect(jsonPath("$[2].numberplate").value("999GLF"));
    }

    @Test
    public void shouldFindById() throws Exception {
        mvc.perform(get("/cars/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.make").value("Skoda"))
                .andExpect(jsonPath("$.model").value("Octavia"))
                .andExpect(jsonPath("$.numberplate").value("999GLF"));
    }

    @Test
    public void shouldReturn404OnFindByIdWithWrongId() throws Exception {
        mvc.perform(get("/cars/15")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
