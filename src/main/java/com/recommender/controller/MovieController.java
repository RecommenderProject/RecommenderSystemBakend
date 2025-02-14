package com.recommender.controller;

import com.recommender.api.MoviesApi;
import com.recommender.model.Movie;
import com.recommender.model.MoviePage;
import com.recommender.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MovieController implements MoviesApi {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public ResponseEntity<MoviePage> moviesGet(Integer page, Integer size) {
        return ResponseEntity.ok(movieService.getAllMovies(page, size));
    }

    @Override
    public ResponseEntity<Movie> moviesPost(Movie movie) {
        return ResponseEntity.ok(movieService.createMovie(movie));
    }

    @Override
    public ResponseEntity<Movie> moviesIdGet(Integer id) {
        Optional<Movie> movie = Optional.ofNullable(movieService.getMovieById(id));
        if (movie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @Override
    public ResponseEntity<Void> moviesIdDelete(Integer id) {
        Optional<Movie> movie = Optional.ofNullable(movieService.getMovieById(id));
        if (movie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        movieService.deleteMovieById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Movie> moviesIdPut(Integer id, Movie movie) {
        Optional<Movie> movieFound = Optional.ofNullable(movieService.getMovieById(id));
        if (movieFound.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieService.updateMovie(id, movie));
    }
}
