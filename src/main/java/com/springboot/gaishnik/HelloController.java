package com.springboot.gaishnik;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	private NumberGenerator NumberGenerator= new NumberGenerator();
	
	@GetMapping("/number/next")
	public String GetNextNumbe() {
		return NumberGenerator.GetNextNumber();
	}
	@GetMapping("/number/random")
	public String GetRandomNumber() {
		return NumberGenerator.GetRandomNumber();
	}
	
	
	@GetMapping("/test")
	public boolean test() {
		return NumberGenerator.setCurrentNumber("A999РХ 116 RUS");
	}
}
