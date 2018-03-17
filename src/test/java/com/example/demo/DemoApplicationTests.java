package com.example.demo;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.junit.LoggerContextRule;
import org.apache.logging.log4j.test.appender.ListAppender;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@ClassRule
	public static LoggerContextRule context = new LoggerContextRule("log4j2.xml");

	private ListAppender listAppender;

	@Before
	public void before() {
		listAppender = context.getListAppender("List1").clear();
	}

	@Test
	public void LoggerTest() {
		MyLogger logger = MyLogger.create(DemoApplicationTests.class);
		final List<LogEvent> events = listAppender.getEvents();
		assertThat(events, hasSize(0));

		logger.info("TEST");
		assertThat(events, hasSize(1));

		assertEquals("TEST", events.get(0).getMessage().toString());
	}

}
