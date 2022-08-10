package com.oocl.easymovie.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderRequest {

    private Long userId;
    private Long movieId;
    private Long theaterId;
    private Long scheduleId;
    private Boolean isTicketUsed;
    private Integer votes;
    private double snacksTotalPrice;
    private Long snacksId;
    private double totalPrice;
    private Boolean isPaid;
    private Boolean isRebook;
    private Boolean isRefund;
    private Date expirationTime;
    private String quickMarkKey;
}
