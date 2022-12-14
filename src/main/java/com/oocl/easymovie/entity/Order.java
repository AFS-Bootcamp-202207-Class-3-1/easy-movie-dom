package com.oocl.easymovie.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "[order]")
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
    private Integer votes;
    private String quickMarkKey;
    private double snacksTotalPrice;
    private Long snacksId;
    private double totalPrice;

    private Boolean isTicketUsed;
    private Boolean isPaid;
    private Boolean isRebook;
    private Boolean isRefund;

    private Date expirationTime;
    private String seats;
    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
}
