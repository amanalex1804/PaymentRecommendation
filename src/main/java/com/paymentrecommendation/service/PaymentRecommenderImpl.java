package com.paymentrecommendation.service;

import com.paymentrecommendation.Util.CommonUtil;
import com.paymentrecommendation.enums.LineOfBusinessEnum;
import com.paymentrecommendation.factory.LineOfBusinessFactory;
import com.paymentrecommendation.lineOfBusiness.LineOfBusiness;
import com.paymentrecommendation.models.*;
import com.paymentrecommendation.validators.InputValidators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PaymentRecommenderImpl implements PaymentRecommender{
    @Override
    public List<PaymentInstrument> recommendPaymentInstruments(User user, Cart cart) {

        List<PaymentInstrument> recommendedPaymentList = new ArrayList<>();

        if(!InputValidators.isValidInput(cart)) {
            return recommendedPaymentList;
        }

        LineOfBusinessEnum lineOfBusiness = cart.getLineOfBusiness();
        LineOfBusinessFactory factory = new LineOfBusinessFactory();

        LineOfBusiness client = factory.getLineOfBusiness(lineOfBusiness);

        boolean upiEnabled = CommonUtil.isUPIEnabled(user);

        List<PaymentInstrument> listOfEligiblePaymentInstuments = client.getEligiblePaymentInstruments(user.getUserPaymentInstrument(),
                upiEnabled, cart.getCartDetail().getCartAmount());

         recommendedPaymentList = client.getRecommendedPaymentInstruments(listOfEligiblePaymentInstuments);
        return recommendedPaymentList;
    }
}
