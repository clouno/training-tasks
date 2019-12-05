package com.clouno;

public class TreeToListConverter {

    private Node first;
    private Node previous;

    public Node convert(Node root) {
        first = null;
        previous = null;

        traverseInOrder(root);

        return first;
    }

    private void traverseInOrder(Node current) {
        if (current != null) {
            traverseInOrder(current.getLeft());
            relink(current);
            traverseInOrder(current.getRight());
        }
    }

    private void relink(Node current) {
        if (first == null) {
            first = current;
        }

        if (previous != null) {
            current.setLeft(previous);
            previous.setRight(current);
        }

        previous = current;
    }
}
