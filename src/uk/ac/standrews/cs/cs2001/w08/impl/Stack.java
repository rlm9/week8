package uk.ac.standrews.cs.cs2001.w08.impl;

import uk.ac.standrews.cs.cs2001.w08.common.StackEmptyException;
import uk.ac.standrews.cs.cs2001.w08.common.StackOverflowException;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IStack;

public class Stack implements IStack {
    private int step;
    private int start;
    private int pos;
    private Data data;
    private Object[] array;

    public Stack(Data data, int step, int start) {
        this.data = data;
        this.start = start;
        this.step = step;
        array = data.getArray();
        pos = start - step;

    }

    @Override
    public void clear() {
        try {
            while (pos != start - step) {
                pop();
            }
        } catch (StackEmptyException e) {

        }

    }

    @Override
    public void push(Object element) throws StackOverflowException {
        if (data.getSpare() == 0) {
            throw new StackOverflowException();
        }
        pos += step;
        data.push();
        array[pos] = element;

    }

    @Override
    public Object pop() throws StackEmptyException {
        if (pos == start - step) {
            throw new StackEmptyException();
        }
        Object pop = array[pos];
        array[pos] = null;
        pos -= step;

        return pop;

    }

    @Override
    public Object top() throws StackEmptyException {
        if (pos == start - step) {
            throw new StackEmptyException();
        }
        return array[pos];
    }

    @Override
    //Returns the size of the stack,by returning the positive diffrence of the start and current position plus 1
    //Example, array size = 10, first stack at position 2, (1+0+(2*1))= 3, 0,1,2, postions array
    //For the second Stack, at postions 8,(1+9+(8*-1))=2, index 8 and 9 in the array
    public int size() {
        return 1 + start + (pos * step);
    }

    @Override
    public boolean isEmpty() {
        return pos == start - step;
    }
}
