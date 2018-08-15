package com.iwe.avengers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDAO;
import com.iwe.avengers.util.AvengerUtil;

public class RemoveAvengerHandler implements RequestHandler<Avenger, HandlerResponse> {

	private AvengerDAO dao = new AvengerDAO();

	@Override
	public HandlerResponse handleRequest(final Avenger avenger, final Context context) {
		context.getLogger().log("[#] - Removing Avenger with id: "+avenger.getId());
		context.getLogger().log("[#] - Avenger: "+avenger);
		
		AvengerUtil.validateAndRetrievesAvenger(avenger.getId(), dao);
		
		context.getLogger().log("[#] - Avenger exists! id: "+avenger.getId());
		
		dao.remove(avenger);
		
		context.getLogger().log("[#] - Avenger removed! id: "+avenger.getId());
		
		final HandlerResponse response = HandlerResponse
												.builder()
												.setStatusCode(204)
												.build();
		context.getLogger().log("[#] - Response:  "+response);
		return response;
	}
}
