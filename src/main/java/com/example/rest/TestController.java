package com.example.rest;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController

@RequestMapping("/test")
public class TestController {
	@GetMapping("/mono")
	public Mono<Integer> getSingleValue(){
		return Mono.just(1).log();
		
	}
	@GetMapping(path="/flux", produces=MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Integer> getValues(){
		return Flux.just(1,2,3,4,5).log() .delayElements(Duration.ofSeconds(2));
		
	}

}
