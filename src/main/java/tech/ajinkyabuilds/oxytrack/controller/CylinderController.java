package tech.ajinkyabuilds.oxytrack.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.ajinkyabuilds.oxytrack.entity.Cylinder;
import tech.ajinkyabuilds.oxytrack.service.CylinderService;

@RestController
@RequestMapping("/cylinder")
@CrossOrigin(origins = "*")
public class CylinderController {

	@Autowired
	private CylinderService cylinderService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getCylinderById(@PathVariable("id") Long id) {
		Cylinder cylinder = cylinderService.getCylinderById(id);
		ResponseEntity<Cylinder> response = new ResponseEntity<Cylinder>(HttpStatus.BAD_REQUEST);

		if (cylinder != null) {
			response = new ResponseEntity<Cylinder>(cylinder, HttpStatus.OK);
		}

		return response;
	}

	@GetMapping("/")
	public ResponseEntity<?> getCylinders(@RequestParam(name = "page") Integer pageNo) {
		List<Cylinder> cylinders = cylinderService.getCylinder(pageNo);
		return new ResponseEntity<List<Cylinder>>(cylinders, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<?> addCylinder(@RequestBody Cylinder cylinder) {
		Cylinder created = cylinderService.createCylinder(cylinder);
		return new ResponseEntity<Cylinder>(created, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCylinder(@PathVariable("id") Long id, @RequestBody Cylinder cylinder) {

		Cylinder updated = cylinderService.updateCylinder(id, cylinder);
		ResponseEntity<Cylinder> response = new ResponseEntity<Cylinder>(HttpStatus.BAD_REQUEST);

		if (updated != null) {
			response = new ResponseEntity<Cylinder>(updated, HttpStatus.OK);
		}

		return response;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCylinder(@PathVariable("id") Long id) {

		Boolean updated = cylinderService.deleteCylinder(id);
		ResponseEntity<?> response = new ResponseEntity<Cylinder>(HttpStatus.BAD_REQUEST);

		if (updated != Boolean.FALSE) {
			response = new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}

		return response;
	}
}
