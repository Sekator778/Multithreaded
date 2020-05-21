package store;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {
    private final UserStorage storage = new UserStorage();

    /**
     * Класс описывает хранилище юзеров.
     */
    public static class ThreadUserStorage extends Thread {
        private final User user1 = new User(1, 100);
        private final User user2 = new User(2, 50);
        private final UserStorage storage;

        public ThreadUserStorage(UserStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            this.storage.add(user1);
        }
    }

    @Test
    public void testAddThreads() throws InterruptedException {
        UserStorage storage = new UserStorage();
        Thread first = new ThreadUserStorage(storage);
        Thread second = new ThreadUserStorage(storage);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(storage.toString(), is("User{id=1, amount=100}"));
    }

    @Test
    public void add() {
        UserStorage userStore = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        User user3 = new User(1, 300);
        assertTrue(userStore.add(user1));
        assertTrue(userStore.add(user2));
        assertFalse(userStore.add(user3));
    }

    @Test
    public void testUpdate() {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(1, 50);
        User user3 = new User(2, 50);
        assertTrue(storage.add(user1));
        assertTrue(storage.update(user2));
        assertFalse(storage.update(user3));
        assertThat(storage.toString(), is("User{id=1, amount=50}"));
    }

    @Test
    public void testDelete() {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 10);
        User user2 = new User(2, 10);
        User user3 = new User(3, 10);
        assertTrue(storage.add(user1));
        assertTrue(storage.add(user2));
        assertTrue(storage.add(user3));
        assertTrue(storage.delete(user3));
        assertTrue(storage.delete(user2));
        assertFalse(storage.delete(user3));
        assertFalse(storage.delete(user2));
        assertThat(storage.toString(), is("User{id=1, amount=10}"));
    }

    @Test
    public void transfer() {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 50);
        assertTrue(storage.add(user1));
        assertTrue(storage.add(user2));
        assertFalse(storage.transfer(2, 3, 100));
        assertFalse(storage.transfer(1, 2, 1000));
        assertTrue(storage.transfer(1, 2, 20));
        assertFalse(storage.transfer(1, 2, 81));
        assertThat(storage.toString(), is("User{id=1, amount=80}User{id=2, amount=70}"));
    }

    /**
     * в два потока переводим все туда потом все обратно
     */
    @Test
    public void transferConcurrency() {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 100);
        storage.add(user1);
        storage.add(user2);
        Thread first = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                storage.transfer(1, 2, 10);
            }
        });
        Thread second = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                storage.transfer(2, 1, 10);
            }
        });
        first.start();
        second.start();
        assertTrue(storage.transfer(1, 2, 100));
        assertTrue(storage.transfer(2, 1, 200));
    }
}