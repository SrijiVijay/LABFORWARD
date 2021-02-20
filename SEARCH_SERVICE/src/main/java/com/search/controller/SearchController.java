package com.search.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.search.exceptionHandlers.ResourceNotFoundException;
import com.search.model.SearchRequestDto;
import com.search.service.SearchService;

/**
 * The SearchController class.
 */
@RestController
@RequestMapping("/api/search")
public class SearchController {

	@Autowired
	private SearchService searchService;

	/**
	 * Method to search frequency of given word and find similar words in notebook
	 * entry.
	 *
	 * @param person the person
	 * @return the person
	 */
	@PutMapping("/{data}")
	public ResponseEntity<?> createPerson(@PathVariable(value = "data") String data,
			@Valid @RequestBody SearchRequestDto searchDto) throws ResourceNotFoundException {
		 Map<String, String> result = new HashMap<String,String>();
		if (searchDto.getInputValue() == null || searchDto.getInputValue() == "" || data == null || data == "") {
		} else {
			result = searchService.searchData(searchDto.getInputValue(), data);
			return new ResponseEntity<Map<String,String>>(result, HttpStatus.OK);
		}
			return new ResponseEntity<Map<String,String>>(result, HttpStatus.BAD_REQUEST);
	}
}
