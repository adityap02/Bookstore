package org.ood.adporwal.bookstore.utils;
import org.ood.adporwal.bookstore.dto.ResponseDTO;
import org.ood.adporwal.bookstore.enums.Constants;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class GenerateErrorResponseDTO {
    public static ResponseDTO generateErrorResponseDTO(String message) {
        return new ResponseDTO(Constants.NOT_APPLICABLE, Constants.FAILURE, message, BigDecimal.ZERO, Constants.NOT_APPLICABLE);
    }
}
