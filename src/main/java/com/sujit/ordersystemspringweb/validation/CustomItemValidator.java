package com.sujit.ordersystemspringweb.validation;

import com.sujit.ordersystemspringweb.model.Error;
import com.sujit.ordersystemspringweb.repository.Item;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
@Profile("custom")
public class CustomItemValidator implements ItemValidator{

    @Override
    public List<Error> validate(Item item) {
        List<Error> errors = new ArrayList<>();

        if ( item.getId()!= null && !Pattern.matches("\\d{1,5}", item.getId().toString() )) {
            errors.add(new Error("itemId", "Provided id is not a digit"));
        }

        if(!Pattern.matches("^\\w{3,30}", item.getItemName())){
            errors.add(new Error("itemName","Item name must be word character upto 30 character long"));

        }
        if(!Pattern.matches("^(\\w{1,}\\s*){1,10}", item.getDescription())){
            errors.add(new Error("description", "Description should not exceed 10 words"));
        }
        if(!Pattern.matches("^(\\w{1,}\\s*){1,3}", item.getStore())){
            errors.add(new Error("store", "Store should be words up to length 3" ));
        }
        if(!Pattern.matches("^\\w{2,30}", item.getBrand())) {
            errors.add(new Error("brand", "Brand should have character length 3 to 30"));
        }
        if(item.getPrice() < 1 && item.getPrice() > 500000000.00){
            errors.add(new Error("price", "Price should be between 1 to 500000000.00"));

        }
        if (item.getDiscount() < 0 && item.getDiscount() >= item.getPrice()) {
            errors.add(new Error("discount", "Discount cannot be negative and cannot be greater than actual price"));
        }
        return errors;
    }
}
