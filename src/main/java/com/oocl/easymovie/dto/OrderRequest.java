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

}
