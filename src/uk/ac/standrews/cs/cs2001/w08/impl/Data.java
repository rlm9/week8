package uk.ac.standrews.cs.cs2001.w08.impl;

public class Data {
    private int spare;
    private Object[] array;

    public Data(int maxsize) {
        spare = maxsize;
        array = new Object[maxsize];
    }

    public Object[] getArray() {
        return array;
    }

    public int getSpare() {
        return spare;
    }

    public void push() {
        spare--;
    }

    public void pop() {
        spare++;
    }


}
