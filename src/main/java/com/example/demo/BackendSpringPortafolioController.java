package com.example.demo;

//import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Employee;
*/
import com.Service.CocheService;
import com.entity.Coche;

@RestController
@RequestMapping("/api/v1")
public class BackendSpringPortafolioController {


	@Autowired
	private CocheService cocheService;

	@GetMapping("/hola")
	public   String   getAllEmployees() {
		String data=cocheService.lista();
		return data;
	}
	@GetMapping("/")
	public String  dataPrincipal() {
		return "eeeeeeeee";
	}
}
