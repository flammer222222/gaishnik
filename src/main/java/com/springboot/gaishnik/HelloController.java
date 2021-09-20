package com.springboot.gaishnik;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.Math;
@RestController
public class HelloController {
	private NumberGenerator NumberGenerator= new NumberGenerator();
	
	@GetMapping("/welcome")
	public String helloWorld() {
		return NumberGenerator.GetNextNumber();
//		return NumberGenerator.GetRandomNumber();
	}
}
