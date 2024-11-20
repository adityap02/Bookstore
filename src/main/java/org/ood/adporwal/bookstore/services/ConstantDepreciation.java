package org.ood.adporwal.bookstore.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Primary
@Component
public class ConstantDepreciation implements DepreciationStrategy{
    @Override
    public BigDecimal depreciateValue() {
        return BigDecimal.valueOf(0.1);
    }
}
