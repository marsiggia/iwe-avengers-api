package com.iwe.avengers.util;

import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.exception.AvengerNotFoundException;
import com.iwe.avengers.dao.AvengerDAO;

public class AvengerUtil {

	public static Avenger validateAndRetrievesAvenger(String avengerId, AvengerDAO dao) {
		final Avenger retrievedAvenger = dao.find(avengerId);
		
		if (retrievedAvenger == null) {
			throw new AvengerNotFoundException("[NotFound] - Avenger id: "+avengerId+" not found");
		}
		
		return retrievedAvenger;
	}
}