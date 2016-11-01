package uk.ac.standrews.cs.cs2001.w08.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uk.ac.standrews.cs.cs2001.w08.common.AbstractFactoryClient;
import uk.ac.standrews.cs.cs2001.w08.common.QueueEmptyException;
import uk.ac.standrews.cs.cs2001.w08.common.QueueFullException;
import uk.ac.standrews.cs.cs2001.w08.interfaces.IPriorityQueue;

/**
 * Tests priority queue implementation.
 */
public class ArrayPriorityQueueTests extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;
    private static IPriorityQueue defaultQueue;


    @Before
    public void before(){
        defaultQueue=getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
    }
    /**
     * Tests that the factory constructs a non-null priority queue.
     */
    @Test
    public void factoryReturnsNonNullDoubleStackObject() {

        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        assertTrue("Failure: IFactory.makePriorityQueue returns null, expected non-null IPriorityQueue object", queue != null);
    }
    @Test
    public void emptySize(){

        assertTrue(defaultQueue.size()==0);
        assertTrue(defaultQueue.isEmpty());
    }

    private void loopaddQue(IPriorityQueue queue, int size)throws QueueFullException{
        for(int i=0;i<size;i++){
            queue.enqueue(i);
        }
    }

    @Test
    public void testSizeMany()throws QueueFullException{
        loopaddQue(defaultQueue,6);
        assertTrue(defaultQueue.size()==6);
        assertFalse(defaultQueue.isEmpty());
    }



    @Test(expected = QueueFullException.class)
    public void testFull()throws QueueFullException{
        loopaddQue(defaultQueue,10);
        assertTrue(defaultQueue.size()==10);
        defaultQueue.enqueue(5);

    }

    @Test
    public void testSizeOne()throws QueueFullException{
        defaultQueue.enqueue(4);
        assertTrue(defaultQueue.size()==1);
        assertFalse(defaultQueue.isEmpty());

    }

    @Test(expected = QueueEmptyException.class)
    public void emptyqueueDequue()throws QueueEmptyException{
        defaultQueue.dequeue();
    }

}
