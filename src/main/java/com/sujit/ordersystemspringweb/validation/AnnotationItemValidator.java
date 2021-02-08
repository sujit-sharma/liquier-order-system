package com.sujit.ordersystemspringweb.validation;

import com.sujit.ordersystemspringweb.model.Error;
import com.sujit.ordersystemspringweb.repository.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.util.List;

@Component
@Profile("annotation")
@RequiredArgsConstructor
public class AnnotationItemValidator implements ItemValidator{

    private final Validator validator;

    @Override
    public List<Error> validate(Item item) {
        return Error.create(validator.validate(item));
    }
}
