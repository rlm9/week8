package uk.ac.standrews.cs.cs2001.w08.impl;

import uk.ac.standrews.cs.cs2001.w08.common.QueueEmptyException;
import uk.ac.standrews.cs.cs2001.w08.common.QueueFullException;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IPriorityQueue;

public class PriorityQueueTwo implements IPriorityQueue {

    @Override
    public void enqueue(Comparable element) throws QueueFullException {

    }

    @Override
    public Comparable dequeue() throws QueueEmptyException {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }
}
