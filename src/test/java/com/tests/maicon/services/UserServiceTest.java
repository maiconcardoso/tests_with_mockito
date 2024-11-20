package com.tests.maicon.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.tests.maicon.Exceptions.ObjectNotFoundException;
import com.tests.maicon.domain.User;
import com.tests.maicon.dtos.UserDto;
import com.tests.maicon.repositories.UserRepository;

@SpringBootTest
public class UserServiceTest {

    private static final Long ID = 1L;
    private static final String NAME = "Maicon";
    private static final String EMAIL = "maicon@gmail.com";
    private static final String PASSWORD = "q2342394723adfsd3459";

    @InjectMocks
    private UserService service;
    
    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDto userDto;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        start();
    }

    @Test
    public void whenFindByIdThenReturnAnUserInstance() {
        // Arrange
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalUser);
        
        // Act
        User sut = service.findById(ID);
        
        // Assert
        Assertions.assertThat(sut).isNotNull();
        Assertions.assertThat(sut).isEqualTo(user);
        Assertions.assertThat(sut.getId()).isEqualTo(ID);
        Assertions.assertThat(sut.getName()).isEqualTo(NAME);
        Assertions.assertThat(sut.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    public void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(repository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Object not found!"));

        try {
            service.findById(ID);
        } catch (ObjectNotFoundException e) {
            Assertions.assertThatException();
            Assertions.assertThat(e.getMessage()).isEqualTo("Object not found!");
        }
    }

    @Test
    public void whenFindAllThenReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> sut = service.findAll();

        Assertions.assertThat(sut).contains(user);
        Assertions.assertThat(sut.get(0)).isEqualTo(user);
        Assertions.assertThat(sut.get(0).getId()).isEqualTo(user.getId());
        Assertions.assertThat(sut.get(0).getName()).isEqualTo(user.getName());
        Assertions.assertThat(sut.get(0).getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        User sut = service.create(userDto);

        Assertions.assertThat(sut).isNotNull();
        Assertions.assertThat(sut).isNotEqualTo(userDto);
        Assertions.assertThat(sut).isEqualTo(user);
        Assertions.assertThat(sut.getId()).isEqualTo(userDto.getId());
        Assertions.assertThat(sut.getName()).isEqualTo(userDto.getName());
        Assertions.assertThat(sut.getEmail()).isEqualTo(userDto.getEmail());

    }

    private void start() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }

}
