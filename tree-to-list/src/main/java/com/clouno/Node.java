package com.clouno;

public class Node<T> {
    private T data;

    private Node left;
    private Node right;

    public Node(T data) {
        this(data, null, null);
    }

    public Node(T data, Node<T> left, Node<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
