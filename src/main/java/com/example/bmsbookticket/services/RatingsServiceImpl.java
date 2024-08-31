package com.example.bmsbookticket.services;

import com.example.bmsbookticket.exceptions.MovieNotFoundException;
import com.example.bmsbookticket.exceptions.UserNotFoundException;
import com.example.bmsbookticket.models.Movie;
import com.example.bmsbookticket.models.Rating;
import com.example.bmsbookticket.models.User;
import com.example.bmsbookticket.repositories.MovieRepository;
import com.example.bmsbookticket.repositories.RatingRepository;
import com.example.bmsbookticket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingsServiceImpl implements RatingsService{
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Override
    public Rating rateMovie(int userId, int movieId, int rating) throws UserNotFoundException, MovieNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User Not Found"));
        Movie movie = movieRepository.findById(movieId).orElseThrow(()-> new MovieNotFoundException(("Movie Not Found")));
        Rating rate = new Rating();
        rate.setRating(rating);
        rate.setUser(user);
        rate.setMovie(movie);
        return ratingRepository.save(rate);
    }

    @Override
    public double getAverageRating(int movieId) throws MovieNotFoundException {
        Movie movie = movieRepository.findById(movieId).orElseThrow(()-> new MovieNotFoundException("Movie Id Not Found"));
        List<Rating> ratings = ratingRepository.findByMovie(movie);
        return ratings.stream().mapToInt(rating -> rating.getRating()).average().orElse(0.0);
    }
}
