package tech.ajinkyabuilds.oxytrack.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.ajinkyabuilds.oxytrack.entity.Volunteer;
import tech.ajinkyabuilds.oxytrack.security.JwtUtil;
import tech.ajinkyabuilds.oxytrack.service.VolunteerService;

@RestController
@RequestMapping("/volunteer")
@CrossOrigin(origins = "*")
public class VolunteerController {

	@Autowired
	private VolunteerService volunteerService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/")
	public ResponseEntity<?> createAccount(@RequestBody Volunteer volunteer) {
		HashMap<String, String> response = new HashMap<String, String>();

		response.put("status", "false");
		response.put("message", "Volunteer account with this username already exists.");
		ResponseEntity<?> responseEntity = new ResponseEntity<HashMap<String, String>>(response,
				HttpStatus.BAD_REQUEST);

		Volunteer account = volunteerService.createVolunteer(volunteer);

		if (account != null) {
			response.clear();
			response.put("status", "true");
			response.put("message", "Account Created Successfully.");
			responseEntity = new ResponseEntity<HashMap<String, String>>(response, HttpStatus.CREATED);
		}

		return responseEntity;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getVolunteerById(@PathVariable("id") Long id) {
		Volunteer volunteer = volunteerService.getVolunteerById(id);
		ResponseEntity<Volunteer> response = new ResponseEntity<Volunteer>(HttpStatus.BAD_REQUEST);

		if (volunteer != null) {
			response = new ResponseEntity<Volunteer>(volunteer, HttpStatus.OK);
		}

		return response;
	}

	@GetMapping("/")
	public ResponseEntity<?> getOwnData(@RequestHeader("Authorization") String authorizationHeader) {
		Volunteer volunteer = volunteerService.readVolunteer(getUsername(authorizationHeader));
		return new ResponseEntity<Volunteer>(volunteer, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateVolunteer(@PathVariable("id") Long id, @RequestBody Volunteer volunteer) {

		Volunteer updated = volunteerService.updateVolunteer(id, volunteer);
		ResponseEntity<Volunteer> response = new ResponseEntity<Volunteer>(HttpStatus.BAD_REQUEST);

		if (updated != null) {
			response = new ResponseEntity<Volunteer>(updated, HttpStatus.OK);
		}

		return response;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteVolunteer(@PathVariable("id") Long id) {

		Boolean deleted = volunteerService.deleteVolunteer(id);
		ResponseEntity<?> response = new ResponseEntity<Volunteer>(HttpStatus.BAD_REQUEST);

		if (deleted != Boolean.FALSE) {
			response = new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}

		return response;
	}

	public String getUsername(String authorizationHeader) {
		return jwtUtil.extractUsername(authorizationHeader.substring(7));
	}
}
