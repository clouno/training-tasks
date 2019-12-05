package com.clouno;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TreeToListConverterTest {

    private TreeToListConverter converter = new TreeToListConverter();

    @Test
    public void shouldConvertTree2ToList() {
        //         5
        //        / \
        //       3   6    --->   1 2 3 4 5 6 7
        //      / \   \
        //     2   4   7
        //    /
        //   1
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2, node1, null);
        Node<Integer> node4 = new Node<>(4);
        Node<Integer> node3 = new Node<>(3, node2, node4);
        Node<Integer> node7 = new Node<>(7);
        Node<Integer> node6 = new Node<>(6, null, node7);
        Node<Integer> node5 = new Node<>(5, node3, node6);

        Node[] expectedSequence = {node1, node2, node3, node4, node5, node6, node7};

        Node first = converter.convert(node5);

        assertDoublyLinkedListInProperSequence(expectedSequence, first);
    }

    @Test
    public void shouldConvertTree1ToList() {
        //          4
        //        /   \
        //       2     6    --->   1 2 3 4 5 6 7
        //      / \   / \
        //     1   3 5    7
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node2 = new Node<>(2, node1, node3);
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node7 = new Node<>(7);
        Node<Integer> node6 = new Node<>(6, node5, node7);
        Node<Integer> node4 = new Node<>(4, node2, node6);

        Node[] expectedSequence = {node1, node2, node3, node4, node5, node6, node7};

        Node first = converter.convert(node4);

        assertDoublyLinkedListInProperSequence(expectedSequence, first);
    }

    @Test
    public void shouldConvertTrivialTreeToList() {
        //          5
        //         /
        //        4
        //       /
        //      3       --->   1 2 3 4 5
        //     /
        //    2
        //   /
        //  1
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2, node1, null);
        Node<Integer> node3 = new Node<>(3, node2, null);
        Node<Integer> node4 = new Node<>(4, node3, null);
        Node<Integer> node5 = new Node<>(5, node4, null);

        Node[] expectedSequence = {node1, node2, node3, node4, node5};

        Node first = converter.convert(node5);

        assertDoublyLinkedListInProperSequence(expectedSequence, first);
    }

    @Test
    public void shouldConvertTrivialTree2ToList() {
        //  1
        //   \
        //    2
        //     \
        //      3      --->   1 2 3 4 5
        //       \
        //        4
        //         \
        //          5
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node4 = new Node<>(4, null, node5);
        Node<Integer> node3 = new Node<>(3, null, node4);
        Node<Integer> node2 = new Node<>(2, null, node3);
        Node<Integer> node1 = new Node<>(1, null, node2);

        Node[] expectedSequence = {node1, node2, node3, node4, node5};

        Node first = converter.convert(node1);

        assertDoublyLinkedListInProperSequence(expectedSequence, first);
    }

    @Test
    public void shouldReturnRootWhenNoLeaves() {
        Node<Integer> root = new Node<>(1);
        assertEquals(root, converter.convert(root));
    }

    @Test
    public void shouldReturnNullWhenNullPassed() {
        assertNull(converter.convert(null));
    }

    private void assertDoublyLinkedListInProperSequence(Node[] expectedSequence, Node first) {
        // Check sequence (begin to end)
        Node current = first;
        Node last = first;
        for (Node expectedNode : expectedSequence) {
            assertEquals(expectedNode, current);

            current = (current == null || current.getRight() == null) ? null : current.getRight();

            if (current != null) {
                last = current;
            }
        }

        // Check sequence in reverse order (end to begin)
        current = last;
        for (int i = expectedSequence.length - 1; i >= 0; i--) {
            assertEquals(expectedSequence[i], current);
            current = (current == null || current.getLeft() == null) ? null : current.getLeft();
        }
    }
}