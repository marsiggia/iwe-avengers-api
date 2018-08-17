package com.iwe.avengers;

import java.util.NoSuchElementException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.exception.AvengerNotFoundException;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDAO;

public class UpdateAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {

	private AvengerDAO dao = AvengerDAO.getInstance();
	
	@Override
	public HandlerResponse handleRequest(final Avenger avenger, final Context context) {
		context.getLogger().log("[#] - Updating Avenger with id: "+avenger.getId());
		context.getLogger().log("[#] - Avenger: "+avenger);
		try {
			dao.find(avenger.getId());
			dao.save(avenger);
		} catch (NoSuchElementException e) {
			throw new AvengerNotFoundException("[NotFound] - Avenger id: "+avenger.getId()+" not found");
		}
		context.getLogger().log("[#] - Avenger updated: "+avenger);
		
		final HandlerResponse response = HandlerResponse
												.builder()
												.setStatusCode(200)
												.setObjectBody(avenger)
												.build();
		context.getLogger().log("[#] - Response:  "+response);
		return response;
	}
}
