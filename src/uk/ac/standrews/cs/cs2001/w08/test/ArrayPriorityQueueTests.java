package uk.ac.standrews.cs.cs2001.w08.test;

import static org.junit.Assert.assertEquals;
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
    public void before() {
        defaultQueue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
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
    public void emptySize() {

        assertTrue(defaultQueue.size() == 0);
        assertTrue(defaultQueue.isEmpty());
    }

    private void loopaddQue(IPriorityQueue queue, int size) throws QueueFullException {
        for (int i = 0; i < size; i++) {
            queue.enqueue(i);
        }
    }

    @Test
    public void testSizeOne() throws QueueFullException {
        defaultQueue.enqueue(4);
        assertTrue(defaultQueue.size() == 1);
        assertFalse(defaultQueue.isEmpty());

    }

    @Test
    public void testSizeMany() throws QueueFullException {
        loopaddQue(defaultQueue, 6);
        assertTrue(defaultQueue.size() == 6);
        assertFalse(defaultQueue.isEmpty());
    }

    @Test(expected = QueueFullException.class)
    public void testFull() throws QueueFullException {
        loopaddQue(defaultQueue, 10);
        assertTrue(defaultQueue.size() == 10);
        defaultQueue.enqueue(5);

    }

    private void loopDeque(IPriorityQueue queue, int size) throws QueueFullException, QueueEmptyException {
        for (int i = 0; i < size; i++) {
            queue.dequeue();
        }
    }


    @Test(expected = QueueEmptyException.class)
    public void emptyqueueDequue() throws QueueEmptyException {
        defaultQueue.dequeue();
    }

    @Test
    public void enqueDequeOne() throws QueueEmptyException, QueueFullException {
        defaultQueue.enqueue(1);
        assertEquals(1, defaultQueue.dequeue());
        assertTrue(defaultQueue.isEmpty());
    }

    @Test
    public void endqueDequeMany() throws QueueFullException, QueueEmptyException {

        defaultQueue.enqueue(6);
        defaultQueue.enqueue(3);
        defaultQueue.enqueue(9);
        defaultQueue.enqueue(5);

        assertEquals(9, defaultQueue.dequeue());

        assertEquals(defaultQueue.size(), 3);

        assertEquals(defaultQueue.dequeue(), 6);
        assertEquals(defaultQueue.dequeue(), 5);
        assertEquals(defaultQueue.dequeue(), 3);

    }

    @Test(expected = QueueEmptyException.class)
    public void clearTest() throws QueueEmptyException, QueueFullException {
        loopaddQue(defaultQueue, 7);
        assertEquals(defaultQueue.size(), 7);
        defaultQueue.clear();
        assertTrue(defaultQueue.isEmpty());
        defaultQueue.dequeue();
    }


}
