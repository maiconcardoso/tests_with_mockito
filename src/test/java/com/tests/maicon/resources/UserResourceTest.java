package com.tests.maicon.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

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

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyLong())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDto);

        ResponseEntity<UserDto> sut = resource.findById(ID);

        Assertions.assertThat(sut).isNotNull();
        Assertions.assertThat(sut.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        Assertions.assertThat(sut.getBody()).isEqualTo(userDto);
        Assertions.assertThat(sut.getBody().getId()).isEqualTo(userDto.getId());
        Assertions.assertThat(sut.getBody().getName()).isEqualTo(userDto.getName());
        Assertions.assertThat(sut.getBody().getEmail()).isEqualTo(userDto.getEmail());
    }

    @Test
    public void whenFindAllThenReturnAListUserDto() {
        when(service.findAll()).thenReturn(List.of(user));
        when(mapper.map(any(), any())).thenReturn(userDto);

        ResponseEntity<List<UserDto>> sut = resource.findAll();

        Assertions.assertThat(sut).isNotNull();
        Assertions.assertThat(sut.getBody()).isNotNull();
        Assertions.assertThat(HttpStatus.OK).isEqualTo(sut.getStatusCode());
        Assertions.assertThat(ResponseEntity.class).isEqualTo(sut.getClass());
        Assertions.assertThat(ArrayList.class).isEqualTo(sut.getBody().getClass());
        Assertions.assertThat(userDto).isEqualTo(sut.getBody().get(0));

        Assertions.assertThat(sut.getBody().get(0).getId()).isEqualTo(userDto.getId());
        Assertions.assertThat(sut.getBody().get(0).getName()).isEqualTo(userDto.getName());
        Assertions.assertThat(sut.getBody().get(0).getEmail()).isEqualTo(userDto.getEmail());
    }

    private void start() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
    }
}
