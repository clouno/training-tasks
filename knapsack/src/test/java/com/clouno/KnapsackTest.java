package com.clouno;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class KnapsackTest {

    @Test
    public void testMyCase1() {
        int[] w = {1, 2, 3};
        int[] v = {2, 3, 1};
        int[] expectedItems = {0 /*2*/, 1 /*3*/};
        int expectedValue = 5;

        Knapsack knapsack = new Knapsack(5);

        assertEquals(expectedValue, knapsack.pack(w, v));
        assertArraysEqualsIgnoringOrder(expectedItems, knapsack.getPackedItems());
    }

    @Test
    public void testMyCase2() {
        int[] w = {2, 3, 1, 4, 7};
        int[] v = {10, 1, 6, 7, 9};
        int[] expectedItems = {0 /*10*/, 2 /*6*/, 3 /*7*/, 4 /*9*/};
        int expectedValue = 32;

        Knapsack knapsack = new Knapsack(15);

        assertEquals(expectedValue, knapsack.pack(w, v));
        assertArraysEqualsIgnoringOrder(expectedItems, knapsack.getPackedItems());
    }

    @Test
    public void testWikipediaCase() {
        int[] w = {12, 1, 2, 1, 4};
        int[] v = {4, 2, 2, 1, 10};
        int[] expectedItems = {1 /*4*/, 2 /*2*/, 3 /*1*/, 4 /*10*/};
        int expectedValue = 15;

        Knapsack knapsack = new Knapsack(15);

        assertEquals(expectedValue, knapsack.pack(w, v));
        assertArraysEqualsIgnoringOrder(expectedItems, knapsack.getPackedItems());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCapacityIsNegative() {
        new Knapsack(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCapacityIsZero() {
        new Knapsack(0);
    }

    private void assertArraysEqualsIgnoringOrder(int[] expectedItems, int[] packedItems) {
        Arrays.sort(expectedItems);
        Arrays.sort(packedItems);
        Assert.assertArrayEquals(expectedItems, packedItems);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenLengthOfInputArraysDifferent() {
        new Knapsack(15).pack(new int[]{ 1, 2, 3 }, new int[]{ 1, 2 });
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenWeightIsNegative() {
        new Knapsack(5).pack(new int[]{ 1, -1, 3 }, new int[]{ 1, 2, 3 });
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenWeightIsZero() {
        new Knapsack(5).pack(new int[]{ 1, 0, 3 }, new int[]{ 1, 2, 3 });
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenWeightGraterThanCapacity() {
        new Knapsack(5).pack(new int[]{ 1, 6, 3 }, new int[]{ 1, 2, 3 });
    }
}