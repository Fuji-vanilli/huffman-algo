package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Node implements Comparable<Node>{
    private final int frequency;
    private Node left;
    private Node right;
    public Node(Node left, Node right) {
        this.frequency= left.getFrequency()+ right.getFrequency();
        this.left= left;
        this.right= right;
    }
    public Node(int frequency) {
        this.frequency= frequency;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.frequency, other.getFrequency());
    }
}
