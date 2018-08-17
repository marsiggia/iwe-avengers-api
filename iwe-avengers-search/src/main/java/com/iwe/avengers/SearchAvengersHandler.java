package com.iwe.avengers;

import java.util.NoSuchElementException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.exception.AvengerNotFoundException;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDAO;

public class SearchAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {

	@Override
	public HandlerResponse handleRequest(final Avenger avenger, final Context context) {
		final String id = avenger.getId();
		context.getLogger().log("[#] - Searching Avenger with id: "+id);
		
		final Avenger retrievedAvenger;
		try {
			retrievedAvenger = AvengerDAO.getInstance().find(id);
		} catch (NoSuchElementException e) {
			throw new AvengerNotFoundException("[NotFound] - Avenger id: "+id+" not found");
		}
		
		context.getLogger().log("[#] - Avenger found! =)");
		final HandlerResponse response = HandlerResponse
													.builder()
													.setStatusCode(200)
													.setObjectBody(retrievedAvenger)
													.build();
		return response;
	}
}
