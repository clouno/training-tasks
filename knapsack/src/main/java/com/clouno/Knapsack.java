package com.clouno;

import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    private int capacity;
    private int[][] memo;
    private int[] packedItems;

    public Knapsack(int capacity) {
        checkCapacity(capacity);
        this.capacity = capacity;
    }

    public int[] getPackedItems() {
        return packedItems;
    }

    public int pack(int[] weights, int[] values) {
        checkInputs(weights, values);

        memoize(weights, values);

        return packItems(weights, values);
    }

    private void memoize(int[] weights, int[] values) {
        int n = weights.length;

        memo = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {

                int value;

                if (weights[i - 1] > j) {
                    value = memo[i - 1][j];
                } else {
                    value = Math.max(memo[i - 1][j], values[i - 1] + memo[i - 1][j - weights[i - 1]]);
                }

                memo[i][j] = value;
            }
        }
    }

    private int packItems(int[] weights, int[] values) {
        List<Integer> items = new ArrayList<>();
        int totalValue = 0;

        int i = weights.length;
        int j = capacity;

        while (i > 0) {

            if (memo[i][j] != memo[i - 1][j]) {
                items.add(i - 1);
                totalValue += values[i - 1];
                j -= weights[i - 1];
            }

            i--;
        }

        this.packedItems = items.stream().mapToInt(e -> e).toArray();

        return totalValue;
    }

    private void checkCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be strictly positive.");
        }
    }

    private void checkInputs(int[] weights, int[] values) {
        if (weights.length != values.length) {
            throw new IllegalArgumentException("Weights and Values must have the same length.");
        }

        for (int value : weights) {
            checkWeight(value);
        }
    }

    private void checkWeight(int weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be strictly positive.");
        }

        if (weight > capacity) {
            throw new IllegalArgumentException("Weight must not be grater than knapsack capacity.");
        }
    }
}
