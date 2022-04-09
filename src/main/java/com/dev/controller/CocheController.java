package com.dev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dev.model.Coche;
import com.dev.model.Person;
import com.dev.repository.CocheRepository;
import com.dev.service.CocheService;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpEntity;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;

import org.springframework.http.ResponseEntity;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class CocheController {

	@Autowired
	private CocheRepository CocheRepository;

	@Autowired
	private CocheService CocheService;
	@GetMapping("/dataPrueba")
	public List<Coche> getDataPrueba() {
		return CocheService.lista();
	}

	/*@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}*/

	@RequestMapping(value = "/template/products")
	public  Object[]  getProductList() {
		Person[] arrayDat=CocheService.listaApi();
		System.out.println(arrayDat[0].email);
	   return arrayDat;
	}

	
}
