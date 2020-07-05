package com.sa4105.javaca2.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class InterceptorWhitelist {
	private static InterceptorWhitelist single_whitelist = null;
	
	public String[] paths = {"", "/", "dbseed", "/assets/**","/api/**", "/api"};

	// static method to create instance of Singleton class 
    public static InterceptorWhitelist getInstance() 
    { 
        if (single_whitelist == null) 
        	single_whitelist = new InterceptorWhitelist(); 
  
        return single_whitelist; 
    } 
    
}
