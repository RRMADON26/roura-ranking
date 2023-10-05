package com.rrmadon.repository;

import com.rrmadon.entity.Ranking;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class RankingRepository implements PanacheMongoRepository<Ranking> {
	public Optional<Ranking> findByUserCodeAndPostCode(String userCode, String postCode) {
		return find("userCode = ?1 and postCode = ?2", userCode, postCode).singleResultOptional();
	}
}
