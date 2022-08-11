package com.oocl.easymovie.mapper;

import com.oocl.easymovie.dto.SeatingRequest;
import com.oocl.easymovie.entity.Seating;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SeatingMapper {
    public Seating toEntity(SeatingRequest seatingRequest) {
        Seating seating = new Seating();
        BeanUtils.copyProperties(seatingRequest, seating);
        return seating;
    }

}
