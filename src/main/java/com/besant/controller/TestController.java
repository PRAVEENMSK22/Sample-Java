package com.besant.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
@GetMapping("/user")
	public String welcome(@RequestParam String userName) {
		return "Welcome Mr. "+userName;
	}
}
