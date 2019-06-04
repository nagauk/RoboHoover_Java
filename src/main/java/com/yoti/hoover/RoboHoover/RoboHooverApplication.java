package com.yoti.hoover.RoboHoover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * *Author  : Nagaraju Ukkalam
 * *Date    : 5/20/2019
 **/
@ComponentScan("com.yoti.hoover.RoboHoover")
@SpringBootApplication
public class RoboHooverApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoboHooverApplication.class, args);
	}

}
