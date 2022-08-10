package com.oocl.easymovie.mapper;

import com.oocl.easymovie.dto.OrderContainMovieTheaterScheduleResponse;
import com.oocl.easymovie.dto.OrderRequest;
import com.oocl.easymovie.dto.OrderResponse;
import com.oocl.easymovie.entity.Movie;
import com.oocl.easymovie.entity.Order;
import com.oocl.easymovie.entity.Schedule;
import com.oocl.easymovie.entity.Theater;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toEntity(OrderRequest orderRequest){
        final Order order = new Order();
        BeanUtils.copyProperties(orderRequest, order);
        return order;
    }

    public OrderResponse toResponse(Order order){
        final OrderResponse response = new OrderResponse();
        BeanUtils.copyProperties(order, response);
        return response;
    }

    public OrderContainMovieTheaterScheduleResponse toOrderContainMovieTheaterSchedule(Order order, Movie movie, Theater theater, Schedule schedule){
        OrderContainMovieTheaterScheduleResponse orderContainMovieTheaterScheduleResponse = new OrderContainMovieTheaterScheduleResponse();
        orderContainMovieTheaterScheduleResponse.setOrder(order);
        orderContainMovieTheaterScheduleResponse.setMovie(movie);
        orderContainMovieTheaterScheduleResponse.setTheater(theater);
        orderContainMovieTheaterScheduleResponse.setSchedule(schedule);
        return orderContainMovieTheaterScheduleResponse;
    }
}
