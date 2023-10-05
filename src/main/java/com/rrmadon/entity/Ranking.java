package com.rrmadon.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;

@MongoEntity(collection = "votes")
@Getter
@Setter
public class Ranking extends BaseEntity{
	private String userCode;
	private String postCode;
	private boolean vote;
}
