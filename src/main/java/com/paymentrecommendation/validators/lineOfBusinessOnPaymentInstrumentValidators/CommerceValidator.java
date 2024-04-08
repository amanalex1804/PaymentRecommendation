package com.paymentrecommendation.validators.lineOfBusinessOnPaymentInstrumentValidators;

import com.paymentrecommendation.Util.CommonUtil;
import com.paymentrecommendation.validators.LineOfBusinessValidators;

public class CommerceValidator implements LineOfBusinessValidators {
    public boolean isCreditCardEligible(Double cartAmount) {
        Double amountRestricted = 250000.00;
        return CommonUtil.amountRestriction(amountRestricted, cartAmount);
    }

    public boolean isDebitCardEligible(Double cartAmount) {

        Double amountRestricted = 200000.00;
        return CommonUtil.amountRestriction(amountRestricted, cartAmount);

    }

    public boolean isUPIEligible(Double cartAmount, boolean upiEnabled) {

        if (!upiEnabled) {
            return false;
        }

        Double amountRestricted = 100000.00;
        return CommonUtil.amountRestriction(amountRestricted, cartAmount);
    }

    public boolean isNetBankingEligible(Double cartAmount) {
        return false;

    }
}
