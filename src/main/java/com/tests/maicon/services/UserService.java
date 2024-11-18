package com.tests.maicon.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.tests.maicon.Exceptions.DataIntegrityVialotionException;
import org.springframework.stereotype.Service;

import com.tests.maicon.Exceptions.ObjectNotFoundException;
import com.tests.maicon.domain.User;
import com.tests.maicon.dtos.UserDto;
import com.tests.maicon.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository repository; 

    public User findById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Object Not Found!"));
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User create(UserDto userDto) {
        findByEmail(userDto);
        return repository.save(mapper.map(userDto, User.class));
    }

    public void findByEmail(UserDto userDto) {
        Optional<User> user = repository.findByEmail(userDto.getEmail());
        if (user.isPresent()) {
            throw new DataIntegrityVialotionException("Email already registered.");
        }
    }

}
