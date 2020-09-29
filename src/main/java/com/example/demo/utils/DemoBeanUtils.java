package com.example.demo.utils;

import org.springframework.beans.BeanUtils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DemoBeanUtils {
	
	public static void mergeObject(Object source, Object target) {
		BeanUtils.copyProperties(source,target);
	}
}
