package org.arones.advice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;

@Provider
public class CustomResponseHandler  implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {

        Map<String, String> errors = new HashMap<>();

        for ( ConstraintViolation<?> violation: exception.getConstraintViolations()){
            String campo = violation.getPropertyPath().toString();
            campo = campo.substring(campo.lastIndexOf(".") + 1);
            errors.put(campo, violation.getMessage());
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errors)
                .build();
    }
}
