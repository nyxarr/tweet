package com.tweet.services.tests;

import org.json.JSONObject;

import com.tweet.services.vote.UpVoteService;

public class VoteTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONObject retour = UpVoteService.upVoteComment("f0f577b30eff4dd8b27556dc0457f14a", "570ecb52559a048d4f24c8c1");
		System.out.println(retour.toString());
	}
}
