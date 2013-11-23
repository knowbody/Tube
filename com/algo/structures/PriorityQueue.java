package com.algo.structures;

public class PriorityQueue {

    /**
     * WHAT IS PQ?
     * So basically PQ helps to speed up processes, let's say you have 4 people in front of you in the shop
     * First takes 15 mins, second 10 mins, 3rd - 3 mins, 4th - 6 mins
     * <-- 15 mins <-- 10 mins <-- 3 mins <-- 6 mins <--
     * So the average waiting time is (15 + (15 + 10) + (15 + 10 + 3) + (15 + 10 + 3 + 6)) / 4 (people) = 25.5 mins
     *
     * if we sort people in the queue by their time we get:
     * <-- 3 mins <-- 6 mins <-- 10 mins <-- 15 mins <--
     * So average in this case:
     * (3 + (3 + 6) + (3 + 6 + 10) + (3 + 6 + 10 + 15)) / 4 = 16.25 mins
     * */

    // array in sorted order, from max at 0 to min at size-1
    private int maxSize;
    private long[] qArray;
    private int itemsNum;

    public PriorityQueue(int s) {
        maxSize = s;
        qArray = new long[maxSize];
        itemsNum = 0;
    }

    // adding item to PQ
    public void add(long item) {
        int i;
        if (itemsNum == 0)
            qArray[itemsNum++] = item; // insert at 0
        else {
            for (i = itemsNum - 1; i >= 0; i--) // start at end,
            {
                if (item > qArray[i]) // if new item larger than array
                    qArray[i + 1] = qArray[i]; // shift upward
                else
                    // if it's smaller - shifting done
                    break;
            }
            qArray[i + 1] = item; // insert it
            itemsNum++;
        } // end else (nItems > 0)
    }

    // checking if PQ is empty
    public boolean isEmpty() {
        return (itemsNum == 0);
    }

    // checking if PQ is full
    public boolean isFull() {
        return (itemsNum == maxSize);
    }

    // removing elements from PQ
    public long remove() {
        return qArray[--itemsNum];
    }

    // checking for minimum in PQ
    public long peekMin() {
        return qArray[itemsNum - 1];
    }


    // testing methods
    public static void main(String[] args) {
        PriorityQueue myPQ = new PriorityQueue(3);

        // adding elements to priority queue
        myPQ.add(34);
        myPQ.add(56);
        myPQ.add(12);

        // checking if the PQ is full
        if (myPQ.isFull())
            System.out.println("PQ is full");

        // printing out minimum element of PQ
        System.out.println("The minimum is " + myPQ.peekMin());

        // deleting all elements from PQ
        while (!myPQ.isEmpty()) {
            long item = myPQ.remove();
            System.out.print(item + " "); // 12, 34, 56
        }
        System.out.println(""); // break line

        // if PQ is empty print out the message
        if (myPQ.isEmpty())
            System.out.println("Your priority queue is empty");
    }
}