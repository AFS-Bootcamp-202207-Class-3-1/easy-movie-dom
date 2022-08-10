package com.oocl.easymovie.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long movieId;
    private Long theaterId;
    private Long scheduleId;
    private Boolean isTicketUsed;
    private Integer votes;
    private String quickMarkKey;
    private double snacksTotalPrice;
    private Long snacksId;
    private double totalPrice;
    private Boolean isPaid;
    private Boolean isRebook;
    private Boolean isRefund;
    private Date expirationTime;
    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
}
