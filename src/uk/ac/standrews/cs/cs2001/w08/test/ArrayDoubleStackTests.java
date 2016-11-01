package uk.ac.standrews.cs.cs2001.w08.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import uk.ac.standrews.cs.cs2001.w08.common.AbstractFactoryClient;
import uk.ac.standrews.cs.cs2001.w08.common.StackEmptyException;
import uk.ac.standrews.cs.cs2001.w08.common.StackOverflowException;
import uk.ac.standrews.cs.cs2001.w08.impl.Stack;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IDoubleStack;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IStack;

/**
 * Tests array collection implementation.
 */
public class ArrayDoubleStackTests extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;
    private static IDoubleStack defaultDoubleStack;
    private static IStack defaultFirstStack;
    private static IStack defaultSecoondStack;
    /**
     * Tests that the factory constructs a non-null double stack.
     */
    @Before
    public void before(){

        defaultDoubleStack=getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        defaultFirstStack=defaultDoubleStack.getFirstStack();
        defaultSecoondStack=defaultDoubleStack.getSecondStack();
    }
    @Test
    public void factoryReturnsNonNullDoubleStackObject() {

        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertTrue("Failure: IFactory.makeDoubleStack returns null, expected non-null IDoubleStack object", doubleStack1 != null);
    }

//    @Test
//    public void testStackSizeZero() {
//
//        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
//
//        IStack stack1 = doubleStack.getFirstStack();
//        IStack stack2 = doubleStack.getSecondStack();
//
//        assertTrue(stack1.size() == 0);
//        assertTrue(stack2.size() == 0);
//    }

    //Tests that the stack is empty upon initialisation
    private void testStackSizeEmpty(IStack stack){
        assertTrue(stack.isEmpty());
        assertTrue(stack.size() == 0);
    }
    @Test
    public void testFirstEmpty (){
        testStackSizeEmpty(defaultFirstStack);
    }

    @Test
    public void testSecondEmpty(){
        testStackSizeEmpty(defaultFirstStack);
    }
    //A loop tp push a given number of objects onto a stack
    private void pushLoop(int loop,IStack stack) throws StackOverflowException{
        for(int i=0;i<loop;i++){
            stack.push(new Object());
        }
    }

    //tests that the size and is empty methods work with elements in the stack
    private void sizeTest(IStack stack,int size)throws StackOverflowException{
        pushLoop(size,stack);
        assertTrue(stack.size()==size);
        assertFalse(stack.isEmpty());
    }
    @Test
    public void fistSize()throws StackOverflowException{
        sizeTest(defaultFirstStack,6);
    }
    @Test
    public void SecondfSize()throws StackOverflowException{
        sizeTest(defaultSecoondStack,4);
    }

    //two tests to see both stacks overflow correctly
    @Test(expected = StackOverflowException.class)
    public void overflowFirst()throws StackOverflowException{
        pushLoop(10,defaultFirstStack);
        assertTrue(defaultFirstStack.size()==DEFAULT_MAX_SIZE);
        defaultFirstStack.push(new Object());
    }
    @Test(expected = StackOverflowException.class)
    public void overflowSecond()throws StackOverflowException{
        pushLoop(10,defaultSecoondStack);
        assertTrue(defaultSecoondStack.size()==DEFAULT_MAX_SIZE);
        defaultSecoondStack.push(new Object());
    }

    //two tests that see if the stacks overflow correctly when the other is also partially filled
    private void halfFillBothStacks() throws StackOverflowException{
        pushLoop(5,defaultFirstStack);
        pushLoop(5,defaultSecoondStack);
        assertTrue(defaultFirstStack.size()==5&&defaultSecoondStack.size()==5);

    }
    @Test(expected = StackOverflowException.class)
    public void overflowFirstBoth()throws StackOverflowException{
        halfFillBothStacks();
        defaultFirstStack.push(new Object());
    }
    @Test(expected = StackOverflowException.class)
    public void overflowSecondBoth()throws StackOverflowException {
        halfFillBothStacks();
        defaultSecoondStack.push(new Object());
    }

    //tests pop and top for both stacks
    @Test(expected = StackEmptyException.class)
    public void emptyPopFirst() throws StackEmptyException{
        defaultFirstStack.pop();
    }
    @Test(expected = StackEmptyException.class)
    public void emptyPopSecond() throws StackEmptyException{
        defaultSecoondStack.pop();
    }
    @Test(expected = StackEmptyException.class)
    public void emptyTopFirst() throws StackEmptyException{
        defaultFirstStack.top();
    }
    @Test(expected = StackEmptyException.class)
    public void emptyTopSecond() throws StackEmptyException{
        defaultSecoondStack.top();
    }
    private void popTest(IStack stack)throws StackEmptyException,StackOverflowException{
        Object element=new Object();
        stack.push(element);
        assertTrue(stack.pop()==element);
        assertTrue(stack.isEmpty());
    }

    @Test
    public void pushPopFirst()throws StackOverflowException,StackEmptyException {
        popTest(defaultFirstStack);
    }

    @Test
    public void pushPopSecond()throws StackOverflowException,StackEmptyException {
        popTest(defaultSecoondStack);
    }

    private void topTest(IStack stack)throws StackEmptyException,StackOverflowException{
        Object element=new Object();
        stack.push(element);
        assertTrue(stack.top()==element);
        assertTrue(stack.size()==1);
    }

    @Test
    public void pushTopFirst()throws StackEmptyException,StackOverflowException {
        topTest(defaultFirstStack);
    }
    @Test
    public void pushTopSecond()throws StackEmptyException,StackOverflowException {
        topTest(defaultSecoondStack);
    }

    private void clearTest(IStack stack,int size)throws StackOverflowException,StackEmptyException{
        pushLoop(size,stack);
        assertTrue(stack.size()==size);
        stack.clear();
        assertTrue(stack.size()==0&&stack.isEmpty());
    }

    @Test
    public void clearTestFirst()throws StackEmptyException,StackOverflowException{
        clearTest(defaultFirstStack,8);
    }
    @Test
    public void clearTestSecond()throws StackEmptyException,StackOverflowException{
        clearTest(defaultSecoondStack,8);
    }


}
