package com.sujit.ordersystemspringweb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private String field;
    private String message;

    public static <T> List<Error> create(Set<ConstraintViolation<T>> violations) {
        return violations.stream().map(e -> new Error(e.getPropertyPath().toString(),e.getMessage())).collect(Collectors.toList());
    }
}
