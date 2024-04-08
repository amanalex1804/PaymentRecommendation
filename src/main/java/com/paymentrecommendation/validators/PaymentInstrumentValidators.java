package com.paymentrecommendation.validators;

import com.paymentrecommendation.enums.LineOfBusinessEnum;
import com.paymentrecommendation.enums.PaymentInstrumentType;

public interface PaymentInstrumentValidators {

    public boolean isEnable(PaymentInstrumentType paymentInstrumentType);

    boolean isEligible(LineOfBusinessEnum lineOfBusinessEnum, boolean upiEnabled, Double cartAmount);
}
