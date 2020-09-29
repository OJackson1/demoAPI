package com.example.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.persistence.domain.band;

@Repository
public interface BandRepository extends JpaRepository<band, Long> {

}
