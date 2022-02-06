package de.quantumrange.openuntis.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MINUTES;

@Component
public class UntisCrawler {

	private static final Logger log = LoggerFactory.getLogger(UntisCrawler.class);

	@Scheduled(fixedRate = 2, timeUnit = MINUTES)
	public void reportCurrentTime() {

	}

}
