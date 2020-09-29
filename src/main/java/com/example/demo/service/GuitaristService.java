package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.GuitaristDTO;
import com.example.demo.exception.GuitaristNotFoundException;
import com.example.demo.persistence.domain.guitarist;
import com.example.demo.persistence.repository.GuitaristRepository;
import com.example.demo.utils.DemoBeanUtils;

@Service
public class GuitaristService {
	
	private GuitaristRepository repo;
	
	private ModelMapper mapper;

	@Autowired
	public GuitaristService(GuitaristRepository repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private GuitaristDTO mapToDTO(guitarist guitarist) {
		return this.mapper.map(guitarist,  GuitaristDTO.class);
	}
	
	private guitarist mapFromDTO(GuitaristDTO guitaristDTO) {
		return this.mapper.map(guitaristDTO, guitarist.class);
	}
	
	//create
	public GuitaristDTO createGuitarist(GuitaristDTO guitaristDTO) {
		guitarist toSave = this.mapFromDTO(guitaristDTO);
		guitarist saved = this.repo.save(toSave);
		return this.mapToDTO(saved);
	}
	//readAll
	public List<GuitaristDTO> getAllGuitarists() {
		return this.repo.findAll()
				.stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}
	
	//readById
	
	public GuitaristDTO getAllGuitaristsById(Long id) {
		guitarist found = this.repo.findById(id).orElseThrow(GuitaristNotFoundException::new);
		return this.mapToDTO(found);
	}
	
	//update
	
	public GuitaristDTO updateGuitaristById(GuitaristDTO guitaristDTO, Long id) {
		guitarist toUpdate = this.repo.findById(id).orElseThrow(GuitaristNotFoundException::new);
		DemoBeanUtils.mergeObject(guitaristDTO, toUpdate);
		return this.mapToDTO(this.repo.save(toUpdate));
	}
	
	//delete
	public boolean deleteGuitaristById(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
}
