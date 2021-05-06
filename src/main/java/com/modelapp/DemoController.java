package com.modelapp;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class DemoController {
	@RequestMapping(value = "example")

	public String example() {
		return "Spring boot Gradle Example";
	}

}
