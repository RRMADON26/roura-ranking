package com.rrmadon.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rrmadon.RankDTO;
import com.rrmadon.entity.Ranking;
import com.rrmadon.integration.users.service.UserUtil;
import com.rrmadon.repository.RankingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


@JBossLog
@ApplicationScoped
public class RankingService extends UserUtil {

	@Inject
	RankingRepository rankingRepository;

	@Inject
	@Channel("vote")
	Emitter<String> emitter;

	@Inject
	ObjectMapper objectMapper;

	@Transactional
	public void vote(String postCode) throws JsonProcessingException {
		RankDTO rankDTO = new RankDTO();

		rankDTO.setPostCode(postCode);
		rankingRepository.findByUserCodeAndPostCode(getUser().getCode(), postCode).ifPresentOrElse(rank -> {
			rank.setVote(!rank.isVote());
			rank.update();

			rankDTO.setVote(rank.isVote());
		}, () -> {
			Ranking to = new Ranking();
			to.setUserCode(getUser().getCode());
			to.setPostCode(postCode);
			to.setVote(true);

			to.persist();

			rankDTO.setVote(true);
		});

		emitter.send(objectMapper.writeValueAsString(rankDTO));
	}
}
