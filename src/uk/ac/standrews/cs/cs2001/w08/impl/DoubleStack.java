package uk.ac.standrews.cs.cs2001.w08.impl;

import uk.ac.standrews.cs.cs2001.w08.interfaces.IDoubleStack;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IStack;



public class DoubleStack implements IDoubleStack {
    private IStack firstStack;
    private IStack secondStack;
    private Object[] data;
    public DoubleStack(int maxsize){
        //data = new Object[maxsize];
        Data data = new Data(maxsize);
        firstStack=new Stack(data,1,0);
        secondStack = new Stack(data,-1,maxsize-1);

    }

    @Override
    public IStack getFirstStack() {
        return firstStack;
    }


    @Override
    public IStack getSecondStack() {
        return secondStack;
    }




}
