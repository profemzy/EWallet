package com.example.ewallet.walletapp.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class DefaultController {

	@RequestMapping(value="/",method=RequestMethod.GET,
			produces=MediaType.TEXT_PLAIN_VALUE)
	@ApiOperation(value = "Home", notes = "Hello", response = String.class)
	public String home() {
		return "Goto Home";
	}
}
