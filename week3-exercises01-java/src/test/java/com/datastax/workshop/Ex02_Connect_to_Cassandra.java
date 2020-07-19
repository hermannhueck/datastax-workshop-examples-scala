package com.datastax.workshop;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.datastax.oss.driver.api.core.CqlSession;

@RunWith(JUnitPlatform.class)
public class Ex02_Connect_to_Cassandra extends ExerciseBase {

        public Ex02_Connect_to_Cassandra() {
                super("Exercise2");
        }

        @Test
        public void should_connect_to_Astra() {

                assertFalse(DBConnection.SECURE_CONNECT_BUNDLE.isEmpty(), "Please fill DBConnection class constants");
                assertFalse(DBConnection.KEYSPACE.isEmpty(), "Please fill DBConnection class constants");
                assertFalse(DBConnection.USERNAME.isEmpty(), "Please fill DBConnection class constants");
                assertFalse(DBConnection.PASSWORD.isEmpty(), "Please fill DBConnection class constants");
                assertTrue(new File(DBConnection.SECURE_CONNECT_BUNDLE).exists(), "File '"
                                + DBConnection.SECURE_CONNECT_BUNDLE + "' has not been found\n"
                                + "To run this sample you need to download the secure bundle file from ASTRA WebPage\n"
                                + "More info here:");
                LOGGER.info("File {} located", DBConnection.SECURE_CONNECT_BUNDLE);

                LOGGER.info("Connected with Keyspace {}", cqlSession.getKeyspace().get());
                LOGGER.info("SUCCESS");
        }
}
