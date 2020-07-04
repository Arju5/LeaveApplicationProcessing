package com.sa4105.javaca2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa4105.javaca2.model.PublicHoliday;

public interface PublicHolidayRepository extends JpaRepository<PublicHoliday, Integer> {
	
	PublicHoliday findById(int id);

}
