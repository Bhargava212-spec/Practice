package com.test;

import java.util.List;

public class Node {
    public int val;
    public Node child;
    public Node prev;
    public Node next;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
