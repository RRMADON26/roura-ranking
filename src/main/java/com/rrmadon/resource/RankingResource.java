package com.rrmadon.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rrmadon.RankDTO;
import com.rrmadon.service.RankingService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@RequestScoped
@Path("/v1/rankings")
public class RankingResource {

	@Inject
	RankingService rankingService;

	@POST
	@Path("/vote")
	public void vote(@QueryParam("postCode") String postCode, RankDTO rankDTO) throws JsonProcessingException {
		rankingService.vote(postCode,rankDTO);
	}

}
