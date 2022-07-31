package com.example.sunspotanalysertest.apirest.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sunspotanalysertest.apirest.dto.GridDTO;
import com.example.sunspotanalysertest.apirest.dto.ScoresDTO;
import com.example.sunspotanalysertest.services.GridService;

@RestController
@RequestMapping(path = "/sun-spot-analyser-api", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneralController {

	@Autowired
	private GridService gridService;

	@GetMapping("/")
	public ResponseEntity<String> hello() {
		return new ResponseEntity<>(
				"Hi. This is a landing page without any other function. You could it to check if app is online using an webbrowser",
				HttpStatus.OK);
	}

	@GetMapping("/scores")
	public ResponseEntity<ScoresDTO> getGrid(@RequestParam Long id) {
		ScoresDTO scores = gridService.findById(id);
		return new ResponseEntity<>(scores, (scores.getScores().isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK));
	}

	@PostMapping("/grid")
	public ResponseEntity<Object> addGrid(@RequestBody(required = true) GridDTO gridDTO) {
		Long id = gridService.addGrid(gridDTO).getId();
		HashMap<String, Long> response = new HashMap<>();
		response.put("id", id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
