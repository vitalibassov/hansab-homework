package com.hansab.carviewer.service;

import com.hansab.carviewer.repository.CarRepository;
import com.hansab.carviewer.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

public class UserServiceTest {

    @Test
    public void shouldThrowIllegalArgumentExceptionOnSearchWithWrongSortOrder() {
        UserService userService = createUserService();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                userService.search("teet", "name:asccc"));

    }

    @Test
    public void shouldThrowRuntimeExceptionOnSearchWithWrongSortSyntax() {
        UserService userService = createUserService();
        Assertions.assertThrows(RuntimeException.class, () ->
                userService.search("teet", "name|asccc"));

    }

    private UserService createUserService() {
        UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);
        CarRepository carRepositoryMock = Mockito.mock(CarRepository.class);
        ModelMapper modelMapperMock = Mockito.mock(ModelMapper.class);

        return new UserService(userRepositoryMock, carRepositoryMock, modelMapperMock);
    }
}
