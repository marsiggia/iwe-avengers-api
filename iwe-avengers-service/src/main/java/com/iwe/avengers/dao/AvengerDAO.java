package com.iwe.avengers.dao;

import java.util.HashMap;
import java.util.Map;

import com.iwe.avenger.dynamodb.entity.Avenger;

public class AvengerDAO {

	public Map<String, Avenger> mapper = new HashMap<>();
	
	public AvengerDAO() {
		mapper.put("1", new Avenger("1", "Iron Man", "Tony Stark"));
		mapper.put("2", new Avenger("2", "Thor - Odin's Son", "Thor"));
	}
	
	public Avenger find(String id) {
		return mapper.get(id);
	}

}
