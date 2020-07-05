package com.sa4105.javaca2.apicontroller;

import java.util.ArrayList;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa4105.javaca2.model.PublicHoliday;
import com.sa4105.javaca2.service.PublicHolidayService;
import com.sa4105.javaca2.service.PublicHolidayServiceImpl;

//Only accessible after u logged in due to interceptor
@RestController
@RequestMapping("/api")
public class PublicHolidayApiController {
	
	@Autowired
	private PublicHolidayService phservice;

	
	public PublicHolidayApiController(PublicHolidayServiceImpl phserviceImpl) {
		this.phservice = phserviceImpl;
	}
	
	@GetMapping("/list")
    public ArrayList<PublicHoliday> getPublicHolidayList() {
		return phservice.findAll();
	}
	
}
