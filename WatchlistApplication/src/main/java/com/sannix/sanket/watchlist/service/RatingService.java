package com.sannix.sanket.watchlist.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class RatingService {
 String apiUrl="https://www.omdbapi.com/?apikey=ca59c2da&t=";
 
 public String getMovieRating(String title) {
	 try {
		 //try to fetch the rating by calling omdb api
		 RestTemplate template=new RestTemplate();
		 //apiUrl+title
		 
		 //ObjectNode is used to store json type object
		 ResponseEntity<ObjectNode> response=template.getForEntity(apiUrl+title, ObjectNode.class);
		 ObjectNode jsonObject= response.getBody();
		 return jsonObject.path("imdbRating").asText();
	 }catch(Exception e){
		 //to user entered will be taken 
		 System.out.println("Either movie name is not available or api is down "+e.getMessage());
		 return null;
	 }
 }
}
