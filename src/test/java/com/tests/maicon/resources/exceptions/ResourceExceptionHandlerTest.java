package com.tests.maicon.resources.exceptions;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.tests.maicon.Exceptions.DataIntegrityVialotionException;
import com.tests.maicon.Exceptions.ObjectNotFoundException;
import com.tests.maicon.resources.Exceptions.ResourceExceptionHandler;
import com.tests.maicon.resources.Exceptions.StandardError;

@SpringBootTest
public class ResourceExceptionHandlerTest {

    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenObjectNotFoundExceptionThenReturnAResponseEntity() {
        ResponseEntity<StandardError> sut = exceptionHandler
                .objectNotFound(new ObjectNotFoundException("Object Not Found!"), new MockHttpServletRequest());

        Assertions.assertThat(sut).isNotNull();
        Assertions.assertThat(sut.getBody()).isNotNull();
        Assertions.assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        Assertions.assertThat(sut.getClass()).isEqualTo(ResponseEntity.class);
        Assertions.assertThat(sut.getBody().getClass()).isEqualTo(StandardError.class);
    }
    
    @Test
    public void whenDataIntegrityVialotionExceptionThenReturnAResponseEntity() {
        ResponseEntity<StandardError> sut = exceptionHandler
                .dataIntegrityVialotion(new DataIntegrityVialotionException("Email already registered."), new MockHttpServletRequest());

        Assertions.assertThat(sut).isNotNull();
        Assertions.assertThat(sut.getBody()).isNotNull();
        Assertions.assertThat(sut.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        Assertions.assertThat(sut.getClass()).isEqualTo(ResponseEntity.class);
        Assertions.assertThat(sut.getBody().getClass()).isEqualTo(StandardError.class);
    }

}
