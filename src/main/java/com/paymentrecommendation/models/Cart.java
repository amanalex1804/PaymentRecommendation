package com.paymentrecommendation.models;

import com.paymentrecommendation.enums.LineOfBusinessEnum;

public class Cart {
    private LineOfBusinessEnum lineOfBusiness;

    private CartDetail cartDetail;

    public Cart(LineOfBusinessEnum lineOfBusiness, CartDetail cartDetail) {
        this.lineOfBusiness = lineOfBusiness;
        this.cartDetail = cartDetail;
    }

    public LineOfBusinessEnum getLineOfBusiness() {
        return lineOfBusiness;
    }

    public void setLineOfBusiness(LineOfBusinessEnum lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    public CartDetail getCartDetail() {
        return cartDetail;
    }

    public void setCartDetail(CartDetail cartDetail) {
        this.cartDetail = cartDetail;
    }
}
