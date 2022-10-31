package com.championdo.torneo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest
class TorneoApplicationTests {

	@Test
	void contextLoads() {
		assertThat("Hello world", is("Hello world"));
	}

}
