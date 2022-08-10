package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.PurchasePoint;
import com.oocl.easymovie.entity.User;
import com.oocl.easymovie.exception.BalanceNotEnough;
import com.oocl.easymovie.repository.PurchasePointRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        Integer newCharge = purchasePointService.rechargeByUserId(user.getId(), "coffee-1-studio");

        //then
        assertEquals(101, newCharge);
    }

    @Test
    void should_deduct_balance_successfully_when_deduct_balance_given_userId_and_price_less_than_balance() {
        //given
        User user = new User();
        PurchasePoint purchasePoint = new PurchasePoint();
        purchasePoint.setBalance(500);
        doReturn(purchasePoint).when(purchasePointRepository).findByUserId(user.getId());

        //when
        purchasePointService.deductBalance(user.getId(), 50);

        //then
        verify(purchasePointRepository, times(1)).save(purchasePoint);
    }

    @Test
    void should_throw_exception_failed_when_deduct_balance_given_userId_and_price_more_than_balance() {
        //given
        User user = new User();
        PurchasePoint purchasePoint = new PurchasePoint();
        purchasePoint.setBalance(50);
        doReturn(purchasePoint).when(purchasePointRepository).findByUserId(user.getId());

        //when
        Exception threwException = null;
        try{
            purchasePointService.deductBalance(user.getId(), 500);
        }catch(Exception exception){
            threwException = exception;
        }

        //then
        assert threwException != null;
        assertEquals(threwException.getMessage(),"Sorry, your credit is running low");
    }
}
