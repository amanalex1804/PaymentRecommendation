package com.paymentrecommendation.lineOfBusiness;

import com.paymentrecommendation.enums.LineOfBusinessEnum;
import com.paymentrecommendation.enums.PaymentInstrumentType;
import com.paymentrecommendation.models.PaymentInstrument;
import com.paymentrecommendation.models.UserPaymentInstrument;
import com.paymentrecommendation.validators.LineOfBusinessValidators;
import com.paymentrecommendation.validators.lineOfBusinessOnPaymentInstrumentValidators.CommerceValidator;

import java.util.ArrayList;
import java.util.List;

public class Commerce implements LineOfBusiness {

    List<PaymentInstrumentType> order = List.of(PaymentInstrumentType.CREDIT_CARD,
            PaymentInstrumentType.UPI, PaymentInstrumentType.DEBIT_CARD);


    @Override
    public boolean isEnable(LineOfBusinessEnum lineOfBusinessEnum) {

        return LineOfBusinessEnum.COMMERCE.equals(lineOfBusinessEnum);
    }

    @Override
    public List<PaymentInstrument> getEligiblePaymentInstruments(UserPaymentInstrument userPaymentInstrument, boolean upiEnabled, Double cartAmount) {
        LineOfBusinessValidators validator = new CommerceValidator();
        CommonHelper commonHelper = new CommonHelper();
        List<PaymentInstrument> eligibleCommercePaymentInstrumentList = commonHelper.getEligiblePaymentInstruments(
                validator, userPaymentInstrument, upiEnabled, cartAmount, LineOfBusinessEnum.COMMERCE);
        return eligibleCommercePaymentInstrumentList; // eturn in sorted order
    }

    @Override
    public List<PaymentInstrument> getRecommendedPaymentInstruments(List<PaymentInstrument> eligiblePaymentInstruments) {
        CommonHelper commonHelper = new CommonHelper();
        List<PaymentInstrument> orderedPaymentInstruments = new ArrayList<>();
        for(PaymentInstrumentType paymentInstrumentType: order) {
            orderedPaymentInstruments.addAll(commonHelper.getFilteredPaymentInstrumentList(paymentInstrumentType,
                    eligiblePaymentInstruments));

        }

        //TODO: add relevance based sorting
        return orderedPaymentInstruments;
    }
}
