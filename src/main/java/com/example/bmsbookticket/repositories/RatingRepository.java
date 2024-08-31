package com.example.bmsbookticket.repositories;

import com.example.bmsbookticket.models.Movie;
import com.example.bmsbookticket.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByMovie(Movie movie);
}
