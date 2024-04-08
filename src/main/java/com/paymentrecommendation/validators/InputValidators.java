package com.paymentrecommendation.validators;

import com.paymentrecommendation.models.Cart;

import java.util.Objects;

public class InputValidators {

    public static boolean isValidInput(Cart cart) {


        if(Objects.isNull(cart)) {
            System.out.println("Empty cart");
            return false;
        }

        if(Objects.isNull(cart.getLineOfBusiness())) {
            throw new RuntimeException("The line of business is not supported");
        }

        if(Objects.isNull(cart.getCartDetail())) {
            System.out.println("Empty cart details");
            return false;
        }

        if(Objects.isNull(cart.getCartDetail().getCartAmount())) {
            System.out.println("Empty cart amount");
            return false;
        }

        return true;


    }
}
