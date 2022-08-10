package com.oocl.easymovie.controller;

import com.oocl.easymovie.dto.PurchasePointResponse;
import com.oocl.easymovie.dto.ResultData;
import com.oocl.easymovie.mapper.PurchasePointMapper;
import com.oocl.easymovie.service.PurchasePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/charge")
public class PurchasePointController {
    @Autowired
    private PurchasePointService purchasePointService;
    @Autowired
    private PurchasePointMapper purchasePointMapper;


    public PurchasePointController(PurchasePointService purchasePointService) {
        this.purchasePointService = purchasePointService;
    }

    @GetMapping("/{id}")
    ResultData<PurchasePointResponse> findBalanceByUserId(@PathVariable Long id) {
        return ResultData.success(purchasePointMapper.toResponse(purchasePointService.findBalanceByUserId(id)));
    }

    @PutMapping("/{id}")
    ResultData<PurchasePointResponse> rechargeByUserId(@PathVariable Long id, @RequestBody String code) {
        return ResultData.success(purchasePointMapper.toResponse(purchasePointService.rechargeByUserId(id, code)));
    }
}
