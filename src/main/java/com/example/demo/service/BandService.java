package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.BandDTO;
import com.example.demo.exception.BandNotFoundException;
import com.example.demo.persistence.domain.band;
import com.example.demo.persistence.repository.BandRepository;
import com.example.demo.utils.DemoBeanUtils;

public class BandService {
	
private BandRepository repo;
	
	private ModelMapper mapper;

	@Autowired
	public BandService(BandRepository repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private BandDTO mapToDTO(band band) {
		return this.mapper.map(band, BandDTO.class);
	}
	
	private band mapFromDTO(BandDTO bandDTO) {
		return this.mapper.map(bandDTO, band.class);
	}
	
	//create
	public BandDTO createBand(BandDTO bandDTO) {
		band toSave = this.mapFromDTO(bandDTO);
		band saved = this.repo.save(toSave);
		return this.mapToDTO(saved);
	}
	//readAll
	public List<BandDTO> getAllBands() {
		return this.repo.findAll()
				.stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}
	
	//readById
	
	public BandDTO getAllBandsById(Long id) {
		band found = this.repo.findById(id).orElseThrow(BandNotFoundException::new);
		return this.mapToDTO(found);
	}
	
	//update
	
	public BandDTO updateBandById(BandDTO bandDTO, Long id) {
		band toUpdate = this.repo.findById(id).orElseThrow(BandNotFoundException::new);
		DemoBeanUtils.mergeObject(bandDTO, toUpdate);
		return this.mapToDTO(this.repo.save(toUpdate));
	}
	
	//delete
	public boolean deleteBandById(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
