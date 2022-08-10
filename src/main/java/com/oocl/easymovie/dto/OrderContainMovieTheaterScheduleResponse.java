package com.oocl.easymovie.dto;

import com.oocl.easymovie.entity.Movie;
import com.oocl.easymovie.entity.Order;
import com.oocl.easymovie.entity.Schedule;
import com.oocl.easymovie.entity.Theater;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderContainMovieTheaterScheduleResponse {
    private Order order;
    private Movie movie;
    private Theater theater;
    private Schedule schedule;
}
