package com.paymentrecommendation.lineOfBusiness;

import com.paymentrecommendation.enums.LineOfBusinessEnum;
import com.paymentrecommendation.enums.PaymentInstrumentType;
import com.paymentrecommendation.factory.PaymentInstrumentValidatorsFactory;
import com.paymentrecommendation.models.PaymentInstrument;
import com.paymentrecommendation.models.UserPaymentInstrument;
import com.paymentrecommendation.validators.LineOfBusinessValidators;
import com.paymentrecommendation.validators.PaymentInstrumentValidators;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CommonHelper {

    public List<PaymentInstrument> getEligiblePaymentInstruments(LineOfBusinessValidators validator,
                                                                 UserPaymentInstrument userPaymentInstrument,
                                                                 boolean upiEnabled, Double cartAmount,
                                                                 LineOfBusinessEnum lineOfBusinessEnum) {
        List<PaymentInstrument> paymentInstrumentList = userPaymentInstrument.getPaymentInstruments();
        List<PaymentInstrument> eligibleList = new ArrayList<>();
        PaymentInstrumentValidatorsFactory factory = new PaymentInstrumentValidatorsFactory();

        for(PaymentInstrument paymentInstrument: paymentInstrumentList) {

            /**
             * to avoid the below way of switch stament(DIscussed during call) is to create the payment instrument type factory. But in those validators
             * we will have to add swtich statement on line of business which is again the same problem we are trying to solve here.


            PaymentInstrumentValidators paymentInstrumentValidators = factory.getPaymentInstrumentValidator(
                    paymentInstrument.getPaymentInstrumentType());
            boolean isEligiblePaymentInstrument = paymentInstrumentValidators.isEligible(lineOfBusinessEnum, upiEnabled, cartAmount);

            **/

            boolean isEligiblePaymentInstrument  = false;

            switch (paymentInstrument.getPaymentInstrumentType()) {
                case CREDIT_CARD: isEligiblePaymentInstrument = validator.isCreditCardEligible(cartAmount); break;
                case DEBIT_CARD: isEligiblePaymentInstrument = validator.isDebitCardEligible(cartAmount); break;
                case UPI : isEligiblePaymentInstrument =  validator.isUPIEligible(cartAmount, upiEnabled); break;
                case NETBANKING: isEligiblePaymentInstrument = validator.isNetBankingEligible( cartAmount); break;
                default: throw new IllegalArgumentException("Payment instument does not exist "+ paymentInstrument.getPaymentInstrumentType());
            }

            if(isEligiblePaymentInstrument) {
                eligibleList.add(paymentInstrument);
            }
        }

        return eligibleList;
    }

    public List<PaymentInstrument> getFilteredPaymentInstrumentList(PaymentInstrumentType paymentInstrumentType,
                                                                    List<PaymentInstrument> listOfPaymentInstruments) {

        List<PaymentInstrument> filteredPaymentInstruments = new ArrayList<>();
        for(PaymentInstrument pm: listOfPaymentInstruments) {
            if(paymentInstrumentType.equals(pm.getPaymentInstrumentType())) {
                filteredPaymentInstruments.add(pm);
            }
        }

        // sorting based on relevance score
        filteredPaymentInstruments.sort(Comparator.comparing(PaymentInstrument::getRelevanceScore).reversed());

        return filteredPaymentInstruments;
    }
}
