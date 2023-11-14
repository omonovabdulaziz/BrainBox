package it.live.brainbox.service;

import it.live.brainbox.entity.Movie;
import it.live.brainbox.entity.RequestMovie;
import it.live.brainbox.entity.enums.Level;
import it.live.brainbox.payload.ApiResponse;
import it.live.brainbox.payload.MovieDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {
    ResponseEntity<ApiResponse> addMovie(MovieDTO movieDTO);

    ResponseEntity<ApiResponse> updateMovie(MovieDTO movieDTO, Long movieId);

    Page<?> getAllMoviePage(int page, int size, Level level, String genre);

    Movie getMovie(Long movieId);

    ResponseEntity<ApiResponse> deleteMovie(Long movie);

    Page<?> searchMovie(String keyWord, int page, int pageSize);

    List<Movie> getAllMovieBySerialId(Long serialId);

    ResponseEntity<ApiResponse> addRequest(String request);
    Page<RequestMovie> getAllRequestPage(int page , int size);

}
