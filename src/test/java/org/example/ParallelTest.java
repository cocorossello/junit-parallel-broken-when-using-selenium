package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParallelTest {

    private static final Logger LOGGER = LogManager.getLogger(ParallelTest.class.getName());

    private static final AtomicBoolean SELENIUM_STARTED = new AtomicBoolean(false);

    private static LocalDateTime start;

    @BeforeAll
    static void setup() {
        LOGGER.info("Starting all tests");
        start = LocalDateTime.now();
    }

    @AfterAll
    static void tearup() throws Exception {
        LOGGER.info("Ending all tests");
        long ellapsed = ChronoUnit.MILLIS.between(start, LocalDateTime.now());
        if (ellapsed < 8_000) {
            throw new Exception("Parrallel execution should be throttled to 2 threads, throttling not working, took " + ellapsed + " ms");
        }
    }

    private void doSomeWork() throws Exception {
        if (!SELENIUM_STARTED.get()) {
            simulateOpenSelenium(); //IMPORTANT  Comment this line to make test work
            SELENIUM_STARTED.set(true);
        }
        LOGGER.info("Starting");
        Thread.sleep(2_000);
        LOGGER.info("Ending");
    }


    @Test
    void sample1() throws Exception {
        doSomeWork();
    }

    @Test
    void sample2() throws Exception {
        doSomeWork();
    }

    @Test
    void sample3() throws Exception {
        doSomeWork();
    }

    @Test
    void sample4() throws Exception {
        doSomeWork();
    }

    @Test
    void sample5() throws Exception {
        doSomeWork();
    }

    @Test
    void sample6() throws Exception {
        doSomeWork();
    }

    @Test
    void sample7() throws Exception {
        doSomeWork();
    }

    @Test
    void sample8() throws Exception {
        doSomeWork();
    }



    private static void simulateOpenSelenium() {
        try {
            new RemoteWebDriver(new URI("https://google.com").toURL(), new ChromeOptions().addArguments("--headless"));
        } catch (Exception e) {
            LOGGER.debug("Ignore error", e);
        }
    }

}