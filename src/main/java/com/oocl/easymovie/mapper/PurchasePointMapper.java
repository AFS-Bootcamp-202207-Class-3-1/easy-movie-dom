package com.oocl.easymovie.mapper;

import com.oocl.easymovie.dto.PurchasePointResponse;
import org.springframework.stereotype.Component;


@Component
public class PurchasePointMapper {

    public PurchasePointResponse toResponse(Integer balance) {
        PurchasePointResponse response = new PurchasePointResponse();
        response.setBalance(balance);
        return response;
    }
}
