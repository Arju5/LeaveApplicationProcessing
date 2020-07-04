package com.sa4105.javaca2.service;

import java.util.ArrayList;

import com.sa4105.javaca2.model.PublicHoliday;

public interface PublicHolidayService {

	public ArrayList<PublicHoliday> findAll();
	public boolean createPublicHoliday(PublicHoliday publicholiday);
	public boolean editPublicHoliday(PublicHoliday publicholiday);
	public void deletePublicHoliday(PublicHoliday publicholiday);
	public PublicHoliday findById(int id);

}
