package com.oocl.easymovie.dto;

import com.oocl.easymovie.entity.Movie;
import lombok.*;

import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MovieContainCastResponse {
    private Movie movie;
    List<Character> directorList;
    List<Character> actorList;
}
