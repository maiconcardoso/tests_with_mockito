package com.tests.maicon.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tests.maicon.domain.User;
import com.tests.maicon.dtos.UserDto;
import com.tests.maicon.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDto.class));
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> dtoList = service.findAll().stream().map((x) -> mapper.map(x, UserDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(service.create(userDto).getId())
                .toUri();
        return ResponseEntity.created(uri).body(mapper.map(userDto, UserDto.class));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto obj) {
        obj.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(service.update(obj), UserDto.class));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
