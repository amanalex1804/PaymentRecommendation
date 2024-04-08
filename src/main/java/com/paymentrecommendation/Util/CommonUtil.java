package com.paymentrecommendation.Util;

import com.paymentrecommendation.models.DeviceContext;
import com.paymentrecommendation.models.User;
import com.paymentrecommendation.models.UserContext;

import java.util.Optional;

public class CommonUtil {

    public static boolean amountRestriction(Double restrictedAmount, Double userAmount) {

        return userAmount <= restrictedAmount;
    }

    public static boolean isUPIEnabled(User user) {
        return Optional.of(user)
                .map(User::getUserContext)
                .map(UserContext::getDeviceContext)
                .map(DeviceContext::isUpiEnabled)
                .orElse(false);
    }
}
