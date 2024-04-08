package com.paymentrecommendation.validators;

public interface LineOfBusinessValidators {

    public boolean isCreditCardEligible(Double cartAmount);

    public boolean isDebitCardEligible(Double cartAmount) ;

    public boolean isUPIEligible(Double cartAmount, boolean upiEnabled);

    public boolean isNetBankingEligible(Double cartAmount);

}
