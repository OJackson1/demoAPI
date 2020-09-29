package com.example.demo.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.GuitaristDTO;
import com.example.demo.service.GuitaristService;

// localhost.8981/guitarist/<whatever-crud-function>

@RestController
@RequestMapping("/guitarist")
public class GuitaristController {
	
	private GuitaristService service;

	public GuitaristController(GuitaristService service) {
		super();
		this.service = service;
	}
	
	//create
	
	@PostMapping("/create")
	public ResponseEntity<GuitaristDTO> create(@RequestBody GuitaristDTO guitaristDTO){
		GuitaristDTO created = this.service.createGuitarist(guitaristDTO);
		
		// returns the entity and passes it back (through the service to repo to domain to db)
		return new ResponseEntity<>(created, HttpStatus.CREATED);
		
	}
	
	//readAll
	
	@GetMapping("/readAll")
	public ResponseEntity<List<GuitaristDTO>> getAllGuitarists() {
		return ResponseEntity.ok(this.service.getAllGuitarists());
	}
	
	//readById
	
	@GetMapping("/read/{id}")
	public ResponseEntity<GuitaristDTO> getGuitaristById(@PathVariable long id) {
		return ResponseEntity.ok(this.service.getAllGuitaristsById(id));
		
	}
	
	//update
	
	@PutMapping("/update/{id}")
	public ResponseEntity<GuitaristDTO> updateGuitaristbyId(@PathVariable long id, 
			@RequestBody GuitaristDTO guitaristDTO) {
		GuitaristDTO updated = this.service.updateGuitaristById(guitaristDTO, id);
		return new ResponseEntity<>(updated,HttpStatus.ACCEPTED);
		
	}
	
	//delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<GuitaristDTO> deleteGuitaristById(@PathVariable Long id){
		return this.service.deleteGuitaristById(id)
		? new ResponseEntity<>(HttpStatus.NO_CONTENT)
		: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)	;	
			}
	
}

	