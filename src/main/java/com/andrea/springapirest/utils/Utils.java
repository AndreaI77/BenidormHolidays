package com.andrea.springapirest.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Utils {

	public static  List<String> parseFotos(String fotos) {
		if(fotos != null && !fotos.trim().isEmpty()) {
		 return   new ArrayList<String>(Arrays.asList(fotos.split(";")));
		}else {
			return new ArrayList<String>();
		}
	}

	public static String generateRandomPassword() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString().substring(0, 8);
	}
}
