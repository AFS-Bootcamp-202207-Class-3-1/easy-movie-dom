package com.oocl.easymovie.repository;

import com.oocl.easymovie.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query(value = "select * from `order` o where o.user_id=?1 and o.is_ticket_used=?2", nativeQuery = true)
    List<Order> findUsedOrderByUserId(Long userId,boolean isUsed);

    @Query(value = "select * from `order` o where o.user_id=?1 and o.is_rebook=?2", nativeQuery = true)
    List<Order> findRebookOrderByUserId(Long userId, boolean isRebook);

    @Query(value = "select * from `order` o where o.user_id=?1 and o.is_paid=?2", nativeQuery = true)
    List<Order> findPaidOrderByUserId(Long userId, boolean isPaid);

    @Query(value = "select * from `order` o where o.user_id=?1 and o.is_refund=?2", nativeQuery = true)
    List<Order> findRefundOrderByUserId(Long userId, boolean isRefund);

    @Query(value = "select * from `order` o where o.user_id=?1", nativeQuery = true)
    List<Order> findAllOrderByUserId(Long userId);

    @Query(value = "select * from `order` o where o.quick_mark_key =?1 and o.is_ticket_used=?2 and o.is_paid=?3", nativeQuery = true)
    Optional<Order> findOrderByQuickMarkKeyAndIsTicketUsedAndIsPaid(String quickMarkKey, Boolean isTicketUsed, Boolean isPaid);
}
