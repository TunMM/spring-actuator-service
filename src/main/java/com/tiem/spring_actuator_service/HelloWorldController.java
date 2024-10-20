package com.tiem.spring_actuator_service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	private static final String format="Hello %s";
	private static final AtomicLong counter = new AtomicLong();
	
	@GetMapping("/hello-world")
	@ResponseBody
	public Greeting sayHello(@RequestParam(defaultValue = "Stranger!", required = false) String name) {
		return new Greeting(counter.incrementAndGet(), String.format(format, name));
	}
}
