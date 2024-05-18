package com.pwsunil.com.SpringSecurityEx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SpringSecurityExApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityExApplication.class, args);
	}

}
