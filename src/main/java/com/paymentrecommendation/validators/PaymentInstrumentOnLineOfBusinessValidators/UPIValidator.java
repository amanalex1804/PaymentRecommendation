package com.paymentrecommendation.validators.PaymentInstrumentOnLineOfBusinessValidators;

import com.paymentrecommendation.enums.LineOfBusinessEnum;
import com.paymentrecommendation.enums.PaymentInstrumentType;
import com.paymentrecommendation.validators.PaymentInstrumentValidators;

public class UPIValidator implements PaymentInstrumentValidators {
    @Override
    public boolean isEnable(PaymentInstrumentType paymentInstrumentType) {
        return PaymentInstrumentType.UPI.equals(paymentInstrumentType);
    }

    @Override
    public boolean isEligible(LineOfBusinessEnum lineOfBusinessEnum, boolean upiEnabled, Double cartAmount) {
        return false;
    }


}
