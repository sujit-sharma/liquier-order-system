package com.sujit.ordersystemspringweb.validation;

import com.sujit.ordersystemspringweb.model.Error;
import com.sujit.ordersystemspringweb.repository.Item;

import java.util.List;

public interface ItemValidator {

    List<Error> validate(Item item);
}
