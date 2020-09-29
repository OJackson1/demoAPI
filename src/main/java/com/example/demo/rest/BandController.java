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

import com.example.demo.dto.BandDTO;
import com.example.demo.service.BandService;

@RestController
@RequestMapping("/Band")
public class BandController {
	
	private BandService service;

	public BandController(BandService service) {
		super();
		this.service = service;
	}
	
	//create
	
	@PostMapping("/create")
	public ResponseEntity<BandDTO> create(@RequestBody BandDTO bandDTO){
		BandDTO created = this.service.createBand(bandDTO);
		
		// returns the entity and passes it back (through the service to repo to domain to db)
		return new ResponseEntity<>(created, HttpStatus.CREATED);
		
	}
	
	//readAll
	
	@GetMapping("/readAll")
	public ResponseEntity<List<BandDTO>> getAllBands() {
		return ResponseEntity.ok(this.service.getAllBands());
	}
	
	//readById
	
	@GetMapping("/read/{id}")
	public ResponseEntity<BandDTO> getBandById(@PathVariable long id) {
		return ResponseEntity.ok(this.service.getAllBandsById(id));
		
	}
	
	//update
	
	@PutMapping("/update/{id}")
	public ResponseEntity<BandDTO> updateBandbyId(@PathVariable long id, 
			@RequestBody BandDTO bandDTO) {
		BandDTO updated = this.service.updateBandById(bandDTO, id);
		return new ResponseEntity<>(updated,HttpStatus.ACCEPTED);
		
	}
	
	//delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BandDTO> deleteBandById(@PathVariable Long id){
		return this.service.deleteBandById(id)
		? new ResponseEntity<>(HttpStatus.NO_CONTENT)
		: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)	;	
			}

}
