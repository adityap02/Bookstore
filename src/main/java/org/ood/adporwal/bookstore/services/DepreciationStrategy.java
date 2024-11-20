package org.ood.adporwal.bookstore.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public interface DepreciationStrategy {

    BigDecimal depreciateValue();

//    default BigDecimal constantDepreciation() {
//        return BigDecimal.valueOf(0.9);
//    }
//
//    default BigDecimal dynamicDepreciation() {
//        //TODO : Implement dynamic depreciation
//        return BigDecimal.valueOf(0.8);
//    }
}
