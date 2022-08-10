package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.PurchasePoint;
import com.oocl.easymovie.entity.User;
import com.oocl.easymovie.repository.PurchasePointRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class PurchaseServiceTest {
    @InjectMocks
    PurchasePointService purchasePointService;

    @Mock
    PurchasePointRepository purchasePointRepository;

    @Test
    void should_return_balance_when_get_balance_by_userId_given_userId() {
        //given
        User user = new User();
        PurchasePoint purchasePoint = new PurchasePoint();
        doReturn(purchasePoint).when(purchasePointRepository).findByUserId(user.getId());

        //when
        Integer balance = purchasePointService.findBalanceByUserId(user.getId());

        //then
        assertEquals(purchasePoint.getBalance(), balance);

    }

    @Test
    void should_return_new_charge_when_recharge_given_userId_and_redeem_code() {
        //given
        User user = new User();
        PurchasePoint purchasePoint = new PurchasePoint();
        purchasePoint.setBalance(100);
        purchasePoint.setHistoryTotal(100);
        doReturn(purchasePoint).when(purchasePointRepository).findByUserId(user.getId());
        doReturn(purchasePoint).when(purchasePointRepository).save(purchasePoint);

        //when
        Integer newCharge = purchasePointService.rechargeByUserId(user.getId(), "1-1-1");

        //then
        assertEquals(101, newCharge);
    }
}
