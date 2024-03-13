package com.sannix.sanket.watchlist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sannix.sanket.watchlist.entity.Movie;
import com.sannix.sanket.watchlist.service.DatabaseService;

@RestController
public class MovieController {

	@Autowired
	DatabaseService databaseService;

	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchListForm(@RequestParam(required = false) Integer id) {
		System.out.println(id);
		String viewName = "watchlistItemForm";

		Map<String, Object> model = new HashMap<>();
		if(id==null) {
			model.put("watchlistitem", new Movie());
		}
		else {
			model.put("watchlistitem", databaseService.getMovieById(id));
		}
		return new ModelAndView(viewName, model);
	}
	
	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchlistForm(@Valid @ModelAttribute("watchlistitem") Movie movie,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ModelAndView("watchlistItemForm");
		}
		Integer id=movie.getId();
		if(id==null) {
			databaseService.create(movie);
		}
		else {
			databaseService.update(movie,id);
		}
		
		RedirectView rd=new RedirectView();
		rd.setUrl("/watchlist");
		return new ModelAndView(rd);
	}
	
	@GetMapping("/watchlist")
	public ModelAndView getWatchlist() {
		String viewName="watchlist";
		Map<String,Object> model=new HashMap<>();
		List<Movie> movieList=databaseService.getAllMovies();
		model.put("watchlistrows", databaseService.getAllMovies());
		model.put("noofmovies",movieList.size());
		return new ModelAndView(viewName,model);
	}
	
	@GetMapping("/deleteWatchlist")
	public ModelAndView deleteWatchlist( Movie movie) {
		databaseService.deleteMovie(movie);
		RedirectView rd=new RedirectView();
		rd.setUrl("/watchlist");
		return new ModelAndView(rd);
	}
}
