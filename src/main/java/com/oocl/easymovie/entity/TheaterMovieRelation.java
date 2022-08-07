package com.oocl.easymovie.entity;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author edward
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TheaterMovieRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long theaterId;
    private Long movieId;
}
