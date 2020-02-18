package com.example.demo.apis;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-info")
public class UserInfoControler {

	@GetMapping
	public ResponseEntity<?> userInfo(Principal principal) {
		return ResponseEntity.ok(principal);
	}
}
