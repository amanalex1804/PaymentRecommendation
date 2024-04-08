package com.paymentrecommendation.factory;

import com.paymentrecommendation.enums.LineOfBusinessEnum;
import com.paymentrecommendation.lineOfBusiness.CCBP;
import com.paymentrecommendation.lineOfBusiness.Commerce;
import com.paymentrecommendation.lineOfBusiness.Investment;
import com.paymentrecommendation.lineOfBusiness.LineOfBusiness;

import java.util.List;

public class LineOfBusinessFactory {

    List<LineOfBusiness> factory = List.of(new CCBP(), new Commerce(), new Investment());

    public LineOfBusiness getLineOfBusiness(LineOfBusinessEnum lineOfBusiness) {

        for(LineOfBusiness lob: factory) {
            if(lob.isEnable(lineOfBusiness)) {
                return lob;
            }
        }
        throw new IllegalArgumentException("Such line of business does not exist "+ lineOfBusiness.name());

    }

}
