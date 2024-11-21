package com.tests.maicon.resources;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.tests.maicon.domain.User;
import com.tests.maicon.dtos.UserDto;
import com.tests.maicon.services.UserService;

@SpringBootTest
public class UserResourceTest {

    private static final Long ID = 1L;
    private static final String NAME = "Maicon";
    private static final String EMAIL = "maicon@gmail.com";
    private static final String PASSWORD = "q2342394723adfsd3459";

    private User user;
    private UserDto userDto;

    @InjectMocks
    private UserResource resource;

    @Mock
    private UserService service;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        start();
    }

    private void start() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
    }
}
