package com.ali.doctors.Doktorlar.Contoller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
@RequestMapping("/doctors")
public class MainController {
	
	@GetMapping("/intro")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?> intro(){
			
		return new ResponseEntity("intro 1",HttpStatus.OK);
	}

	@GetMapping("/intro2")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> intro2(){
			
		return new ResponseEntity("intro 2",HttpStatus.OK);
	}
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> singUp(){
		
		return new ResponseEntity("sign-up",HttpStatus.OK);
	}
}
