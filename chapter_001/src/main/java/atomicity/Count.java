package atomicity;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Класс счетчик.
 * @ThreadSafe - эта аннотация, говорит пользователям данного класса, что класс можно использовать в многопоточном режиме
 * и он будет работать правильно.
 *
 * GuardedBy("this") - эта аннотация выставляется над общим ресурсом. Аннотация имеет входящий параметр. Он указывает на объект монитора,
 * по которому мы будет синхронизироваться.
 * Программист должен работать с этим ресурсом только в критической секции, которая синхронизируется по объекту монитора, который указан в аннотации.
 */


@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public synchronized void increment() {
        this.value++;
    }

    public synchronized int get() {
        return this.value;
    }
}