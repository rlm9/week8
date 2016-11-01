package uk.ac.standrews.cs.cs2001.w08.impl;

import uk.ac.standrews.cs.cs2001.w08.common.QueueEmptyException;
import uk.ac.standrews.cs.cs2001.w08.common.QueueFullException;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IPriorityQueue;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class PriorityQueue implements IPriorityQueue {

    private Comparable[] array;
    private int size;

    public PriorityQueue(int maxsize) {
        array = new Comparable[maxsize];
        size = 0;
    }

    @Override
    public void enqueue(Comparable element) throws QueueFullException {
        int index;

        if (element == null) {
            throw new IllegalArgumentException();

        } else if (size == array.length) {
            throw new QueueFullException();

        } else if (size == 0) {
            index = 0;
        } else {

            index = Arrays.binarySearch(array, 0, size, element);
            if (index < 0) {
                index = (index * -1) - 1;
            }
            //     System.out.println(size);
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
        }
        size++;
        array[index] = element;


//        if (size < array.length){
//            array[size()]=element;
//            Arrays.sort(array,Comparator.nullsLast(Comparator.naturalOrder()));
//            size++;
//        }else {
//            throw new QueueFullException();
//        }

    }


    @Override
    public Comparable dequeue() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException();
        }
        Comparable element = array[size - 1];
        array[size - 1] = null;
        size--;
        return element;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;

    }

}
