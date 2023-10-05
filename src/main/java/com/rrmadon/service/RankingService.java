package com.rrmadon.service;

import com.rrmadon.entity.Ranking;
import com.rrmadon.integration.users.service.UserUtil;
import com.rrmadon.repository.RankingRepository;
import com.rrmadon.util.AuthenticationUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RankingService extends UserUtil {

	@Inject
	RankingRepository rankingRepository;

	@Transactional
	public void vote(String postCode) {
		rankingRepository.findByUserCodeAndPostCode(getUser().getCode(), postCode).ifPresentOrElse(rank -> {
			rank.setVote(!rank.isVote());

			rank.update();
		}, () -> {
			Ranking to = new Ranking();
			to.setUserCode(getUser().getCode());
			to.setPostCode(postCode);
			to.setVote(true);

			to.persist();
		});

		//TODO : update upvote and downvote on post
	}
}
