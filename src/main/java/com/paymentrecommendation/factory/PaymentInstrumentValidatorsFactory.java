package com.paymentrecommendation.factory;

import com.paymentrecommendation.enums.PaymentInstrumentType;
import com.paymentrecommendation.validators.*;
import com.paymentrecommendation.validators.PaymentInstrumentOnLineOfBusinessValidators.CreditCardValidator;
import com.paymentrecommendation.validators.PaymentInstrumentOnLineOfBusinessValidators.DebitCardValidator;
import com.paymentrecommendation.validators.PaymentInstrumentOnLineOfBusinessValidators.NetBankingValidator;
import com.paymentrecommendation.validators.PaymentInstrumentOnLineOfBusinessValidators.UPIValidator;

import java.util.List;

public class PaymentInstrumentValidatorsFactory {
    List<PaymentInstrumentValidators> factory = List.of(new UPIValidator(),
            new CreditCardValidator(), new DebitCardValidator(), new NetBankingValidator());

    public PaymentInstrumentValidators getPaymentInstrumentValidator(PaymentInstrumentType paymentInstrumentType) {

        for(PaymentInstrumentValidators paymentInstrumentValidators: factory) {
            if(paymentInstrumentValidators.isEnable(paymentInstrumentType)) {
                return paymentInstrumentValidators;
            }
        }
        throw new IllegalArgumentException("Such line of business does not exist "+ paymentInstrumentType.name());

    }

}
