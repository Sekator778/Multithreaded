package nonblocking;

/**
 *
 */

public class CASCounterAtomicIntegerImpl {
    int currentValue;
    int previousValue;

    /**
     * Creates a new CASCounterAtomicIntegerImpl and is initialized to 0.
     */
    public CASCounterAtomicIntegerImpl() {
        currentValue = 0;
    }

    /**
     * Creates a new CASCounterAtomicIntegerImpl and is initialized to specified initialValue.
     *
     * @param initialValue
     */
    public CASCounterAtomicIntegerImpl(int initialValue) {
        currentValue = initialValue;
    }

    /**
     * method returns the current value
     */
    public synchronized int get() {
        return currentValue;
    }

    /**
     * method set newValue
     */
    public synchronized void set(int newValue) {
        currentValue = newValue;
    }

    /**
     * Sets to newValue and returns the old value.
     */
    public synchronized int getAndSet(int newValue) {
        previousValue = currentValue;
        currentValue = newValue;
        return previousValue;
    }

    /**
     * Compare with expected, if equal, set to update and return true.
     */
    public synchronized boolean compareAndSet(int expected, int update) {
        boolean result = false;
        if (currentValue == expected) {
            currentValue = update;
            result = true;
        }
        return result;
    }

    /**
     * increments current value by 1. And return updated value.
     */
    public synchronized int incrementAndGet() {
        return ++currentValue;
    }

    /**
     * Method return current value. And adds value to the current value.
     */
    public synchronized int getAndAdd(int value) {
        previousValue = currentValue;
        currentValue += value;
        return previousValue;
    }

    /**
     * Method return current value. And increments current value by 1.
     */
    public synchronized int getAndIncrement() {
        return currentValue++;
    }
    /**
     * decrements current value by 1. And return updated value.
     */
    public synchronized int decrementAndGet() {
        return --currentValue;
    }
    /**
     * Method return current value. And decrements current value by 1.
     */
    public synchronized int getAndDecrement() {
        return currentValue--;
    }
    @Override
    public String toString() {
        return "CASCounterAtomicIntegerImpl = " + currentValue;
    }
}
