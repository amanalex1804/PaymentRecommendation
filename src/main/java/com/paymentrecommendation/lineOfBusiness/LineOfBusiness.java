package com.paymentrecommendation.lineOfBusiness;

import com.paymentrecommendation.enums.LineOfBusinessEnum;
import com.paymentrecommendation.models.PaymentInstrument;
import com.paymentrecommendation.models.UserPaymentInstrument;

import java.util.List;

public interface LineOfBusiness {

    public boolean isEnable(LineOfBusinessEnum lineOfBusinessEnum);

    public List<PaymentInstrument> getEligiblePaymentInstruments(UserPaymentInstrument userPaymentInstrument, boolean upiEnabled, Double cartAmount);

    public List<PaymentInstrument> getRecommendedPaymentInstruments(List<PaymentInstrument> eligiblePaymentInstruments);
}
