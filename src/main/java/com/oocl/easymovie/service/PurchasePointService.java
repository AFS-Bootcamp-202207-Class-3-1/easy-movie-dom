package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.PurchasePoint;
import com.oocl.easymovie.exception.UserNotFoundException;
import com.oocl.easymovie.repository.PurchasePointRepository;
import com.oocl.easymovie.util.ParseCode;
import org.springframework.stereotype.Service;

@Service
public class PurchasePointService {

    private final PurchasePointRepository purchasePointRepository;

    public PurchasePointService(PurchasePointRepository purchasePointRepository) {
        this.purchasePointRepository = purchasePointRepository;
    }


    public Integer findBalanceByUserId(Long id) {
        PurchasePoint purchasePoint = purchasePointRepository.findByUserId(id);
        if (purchasePoint == null) {
            throw new UserNotFoundException();
        }
        return purchasePoint.getBalance();
    }

    public Integer rechargeByUserId(Long id, String code) {
        PurchasePoint purchasePoint = purchasePointRepository.findByUserId(id);
        if (purchasePoint == null) {
            throw new UserNotFoundException();
        }
        Integer balance = purchasePoint.getBalance();
        Integer historyTotal = purchasePoint.getHistoryTotal();
        int recharge = ParseCode.parseCode(code);
        purchasePoint.setBalance(balance + recharge);
        purchasePoint.setHistoryTotal(historyTotal + recharge);
        purchasePointRepository.save(purchasePoint);
        return balance + recharge;
    }
}
