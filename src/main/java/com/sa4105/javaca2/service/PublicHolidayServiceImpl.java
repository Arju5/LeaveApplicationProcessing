package com.sa4105.javaca2.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa4105.javaca2.model.PublicHoliday;
import com.sa4105.javaca2.repo.PublicHolidayRepository;

@Service
public class PublicHolidayServiceImpl implements PublicHolidayService {

	@Autowired
	private PublicHolidayRepository phrepo;
	
	@Override
	public ArrayList<PublicHoliday> findAll() {
		ArrayList<PublicHoliday> list = (ArrayList<PublicHoliday>)phrepo.findAll();
		return list;
	}
	
	@Override
	public boolean createPublicHoliday(PublicHoliday publicholiday) {
		if (phrepo.save(publicholiday)!=null) 
			return true;
		else
			return false;
	}
	
	@Override
	public boolean editPublicHoliday(PublicHoliday publicholiday) {
		if (phrepo.save(publicholiday)!=null)
			return true;
		else
			return false;
	}
	
	@Override
	public void deletePublicHoliday(PublicHoliday publicholiday) {
		phrepo.delete(publicholiday);
	}

	@Override
	public PublicHoliday findById(int id) {
		return phrepo.findById(id);
	}

	
}
