package com.example.demo.persistence.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity 
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class band {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name ="band_name", unique = true)
	@NotNull
	@Size(min =1, max = 120) //varchar(20)
	private String name;
	
	@Column(name = "members")
	@Min(1)
	@Max(30)
	private int noOfMembers;
	
	@OneToMany(targetEntity = band.class, cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<band> bands = new ArrayList<>();

	// constructor for making a 'blank' band
	public band(String name) {
		super();
		this.name = name;
	}

	public band(@NotNull @Size(min = 1, max = 120) String name, @Min(1) @Max(30) int noOfMembers, List<band> bands) {
		super();
		this.name = name;
		this.noOfMembers = noOfMembers;
		this.bands = bands;
	}
	
	
	
	


}
