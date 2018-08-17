package com.iwe.avengers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDAO;

public class CreateAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {

	AvengerDAO dao = AvengerDAO.getInstance();
	
	@Override
	public HandlerResponse handleRequest(final Avenger newAvenger, final Context context) {

		context.getLogger().log("[#] INITIATE REGISTRY");
		
		Avenger avengerSaved = dao.save(newAvenger);
		
		final HandlerResponse response = HandlerResponse
													.builder()
													.setStatusCode(200)
													.setObjectBody(avengerSaved)
													.build();
		
		context.getLogger().log("[#] FINALIZING REGISTRY");
		return response;

	}
}
