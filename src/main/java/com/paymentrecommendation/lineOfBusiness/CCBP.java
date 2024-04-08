package com.paymentrecommendation.lineOfBusiness;

import com.paymentrecommendation.enums.LineOfBusinessEnum;
import com.paymentrecommendation.enums.PaymentInstrumentType;
import com.paymentrecommendation.models.PaymentInstrument;
import com.paymentrecommendation.models.UserPaymentInstrument;
import com.paymentrecommendation.validators.LineOfBusinessValidators;
import com.paymentrecommendation.validators.lineOfBusinessOnPaymentInstrumentValidators.CCBPValidators;

import java.util.ArrayList;
import java.util.List;

public class CCBP implements LineOfBusiness {
    List<PaymentInstrumentType> order = List.of(PaymentInstrumentType.UPI,
            PaymentInstrumentType.NETBANKING, PaymentInstrumentType.DEBIT_CARD);

    @Override
    public boolean isEnable(LineOfBusinessEnum lineOfBusinessEnum) {
        return LineOfBusinessEnum.CREDIT_CARD_BILL_PAYMENT.equals(lineOfBusinessEnum);
    }

    @Override
    public List<PaymentInstrument> getEligiblePaymentInstruments(UserPaymentInstrument userPaymentInstrument, boolean upiEnabled, Double cartAmount) {

        LineOfBusinessValidators validator = new CCBPValidators();
        CommonHelper commonHelper = new CommonHelper();
        List<PaymentInstrument> eligibleCCPBList = commonHelper.getEligiblePaymentInstruments(
                validator, userPaymentInstrument, upiEnabled, cartAmount, LineOfBusinessEnum.CREDIT_CARD_BILL_PAYMENT);
        return eligibleCCPBList;
    }

    @Override
    public List<PaymentInstrument> getRecommendedPaymentInstruments(List<PaymentInstrument> eligiblePaymentInstruments) {


        // D1, N1, UPI1, UPI2

        // UPI1, UPI2, N1, D1

        CommonHelper commonHelper = new CommonHelper();
        List<PaymentInstrument> orderedPaymentInstruments = new ArrayList<>();
        for(PaymentInstrumentType paymentInstrumentType: order) {
            orderedPaymentInstruments.addAll(commonHelper.getFilteredPaymentInstrumentList(paymentInstrumentType,
                    eligiblePaymentInstruments));

        }

        return orderedPaymentInstruments;
    }
}
