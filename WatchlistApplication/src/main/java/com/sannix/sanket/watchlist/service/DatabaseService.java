package com.sannix.sanket.watchlist.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sannix.sanket.watchlist.entity.Movie;
import com.sannix.sanket.watchlist.repository.MovieRepo;

@Service
public class DatabaseService {

	@Autowired
	MovieRepo movieRepo;
	
	@Autowired
	RatingService ratingService;

	public void create(Movie movie) {
		String rating=ratingService.getMovieRating(movie.getTitle());
		if(rating!=null) {
			movie.setRating(Float.parseFloat(rating));
		}
		// TODO Auto-generated method stub
		movieRepo.save(movie);
	}
	public List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		return movieRepo.findAll();
	}

	public void deleteMovie(Movie movie) {
		movieRepo.deleteById(movie.getId());
	}
	public Movie getMovieById(Integer id) {
		return movieRepo.findById(id).get();
	}
	public void update(Movie movie, Integer id) {
		Movie toBeUpdated=getMovieById(id);
		toBeUpdated.setTitle(movie.getTitle());
		toBeUpdated.setRating(movie.getRating());
		toBeUpdated.setPriority(movie.getPriority());
		toBeUpdated.setComment(movie.getComment());
		
		movieRepo.save(toBeUpdated);
	}
}
