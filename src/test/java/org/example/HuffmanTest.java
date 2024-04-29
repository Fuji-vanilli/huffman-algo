package org.example;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class HuffmanTest {
    private Huffman huffman;
    private String encodedText;
    public static Instant startAt;

    @BeforeEach
    public void initializer() {
        System.out.println("initailzer of the class here!");
        huffman= new Huffman("aaabbbbcbcba");
        encodedText= huffman.encode();
    }

    @BeforeAll
    public static void initStartExecutionTime() {
        startAt= Instant.now();
        System.out.println("execution start at: "+startAt);
    }

    @AfterAll
    public static void finalExecutionTime() {
        final Instant endAt= Instant.now();
        final long duration= Duration.between(startAt, endAt).toMillis();
        System.out.println("Duration of execution: "+duration+"ms");
    }

    @Test
    public void characterFrequenciesTest() {
        assertEquals(Map.of('a', 4, 'b', 6, 'c', 2),huffman.getCharacterFrequencies());
    }

    @Test
    public void encodedTextTest() {
        //assertEquals("111111000010010011", encodedText);
        assertThat(encodedText).isEqualTo("111111000010010011");
    }
    @Timeout(1)
    @Test
    public void decodedTextTest() {
        final String decodedText= huffman.decode("111111000010010011");
        //assertEquals("aaabbbbcbcba", huffman.decode("111111000010010011"));
        assertThat("aaabbbbcbcba").isEqualTo(decodedText);
    }
}