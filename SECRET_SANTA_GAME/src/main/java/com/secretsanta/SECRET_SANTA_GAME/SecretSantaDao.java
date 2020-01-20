package com.secretsanta.SECRET_SANTA_GAME;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecretSantaDao {

	public List<String> getAllChilds() {
		List<String> allChilds = new ArrayList();
		allChilds.add("ram");
		allChilds.add("shweta");
		allChilds.add("kajal");
		allChilds.add("sapan");
		allChilds.add("vikas");
		allChilds.add("mahesh");
		allChilds.add("aritra");
		Collections.shuffle(allChilds);
		return allChilds;
	}

}
