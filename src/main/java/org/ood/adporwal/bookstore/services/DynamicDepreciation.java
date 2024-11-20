package org.ood.adporwal.bookstore.services;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class DynamicDepreciation implements DepreciationStrategy {
    @Override
    public BigDecimal depreciateValue() {
        //TODO : Implement dynamic depreciation
        return BigDecimal.valueOf(0.2);
    }

}
