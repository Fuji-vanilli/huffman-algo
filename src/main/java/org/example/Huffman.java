package org.example;

import lombok.Getter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class Huffman {
    private Node root;
    private final String text;
    private Map<Character, Integer> characterFrequencies;
    private final Map<Character, String> huffmanCode;
    public Huffman(String text) {
        this.text= text;
        fillCharacterFrequencies();
        huffmanCode= new HashMap<>();
    }
    private void fillCharacterFrequencies() {
        characterFrequencies= text.chars()
                .mapToObj(c-> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        v-> v.getValue().intValue()
                ));
    }
    public String encode() {
        Queue<Node> queueNode= new PriorityQueue<>();
        characterFrequencies.forEach(
                (character, frequency)-> queueNode.offer(new Leaf(character, frequency))
        );

        while (queueNode.size()> 1) {
            queueNode.offer(new Node(queueNode.poll(), Objects.requireNonNull(queueNode.poll())));
        }
        this.root= queueNode.poll();
        generateHuffmanCode(this.root, "");

        return getEncodedText();
    }

    private void generateHuffmanCode(Node node, String code) {
        if (node instanceof Leaf leaf) {
            huffmanCode.put(leaf.getCharacter(), code);
            return;
        }

        generateHuffmanCode(node.getLeft(), code.concat("0"));
        generateHuffmanCode(node.getRight(), code.concat("1"));
    }
    private String getEncodedText() {
        StringBuilder sb= new StringBuilder();
        text.chars().forEach(
                character-> {
                    sb.append(huffmanCode.get((char)character));
                }
        );

        return sb.toString();
    }
    public String decode(String encodedText) {
        StringBuilder sb= new StringBuilder();
        Node current= this.root;

        for (char character: encodedText.toCharArray()) {
            current= character== '0'? current.getLeft(): current.getRight();
            if (current instanceof Leaf leaf) {
                sb.append(leaf.getCharacter());
                current= this.root;
            }
        }

        return sb.toString();
    }

    public void primeTest() {

    }
}
