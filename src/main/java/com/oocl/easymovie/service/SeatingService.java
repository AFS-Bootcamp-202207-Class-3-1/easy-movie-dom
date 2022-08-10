package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.Seating;
import com.oocl.easymovie.exception.SeatingNotFoundException;
import com.oocl.easymovie.repository.SeatingRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SeatingService {
    @Autowired
    SeatingRepository seatingRepository;

    public Seating findSeatingById(Long id){
        return seatingRepository.findById(id).orElseThrow(SeatingNotFoundException::new);
    }
}
