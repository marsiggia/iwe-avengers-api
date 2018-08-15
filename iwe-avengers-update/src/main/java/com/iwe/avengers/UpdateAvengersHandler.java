package com.iwe.avengers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDAO;
import com.iwe.avengers.util.AvengerUtil;

public class UpdateAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {

	private AvengerDAO dao = new AvengerDAO();
	
	@Override
	public HandlerResponse handleRequest(final Avenger avenger, final Context context) {
		context.getLogger().log("[#] - Updating Avenger with id: "+avenger.getId());
		context.getLogger().log("[#] - Avenger: "+avenger);
		
		AvengerUtil.validateAndRetrievesAvenger(avenger.getId(), dao);
		
		context.getLogger().log("[#] - Avenger exists! id: "+avenger.getId());
		
		dao.update(avenger);
		
		final HandlerResponse response = HandlerResponse
				.builder()
				.setStatusCode(200)
				.setObjectBody(avenger)
				.build();
		context.getLogger().log("[#] - Response:  "+response);
		return response;
	}
}
