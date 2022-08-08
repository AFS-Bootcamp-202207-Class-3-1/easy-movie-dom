package com.oocl.easymovie.service;

import com.oocl.easymovie.entity.Theater;
import com.oocl.easymovie.exception.TheaterNotFoundException;
import com.oocl.easymovie.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TheaterService {

    private final TheaterRepository theaterRepository;

    public Theater findTheaterById(Long theaterId) {
        return theaterRepository.findById(theaterId).orElseThrow(TheaterNotFoundException::new);
    }
}
