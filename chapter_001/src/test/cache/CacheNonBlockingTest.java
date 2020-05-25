package cache;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class CacheNonBlockingTest {
    private final Base model = new Base(1, 1);
    /**
     * !! NO
     * Тест выполняется успешно. Это связано с тем, что главная нить не видит,
     * что происходит во второстепенной нити.
     * Чтобы это поправить нам нужно передать исключение к главную нить.
     */
    @Ignore
    public void whenThrowException() throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    throw new RuntimeException("Throw Exception in Thread");
                }
        );
        thread.start();
        thread.join();
    }

    /**
     * Теперь мы можем проверить, что такой код падает.
     */
    @Test
    public void whenThrowException2() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(
                () -> {
                    try {
                        throw new RuntimeException("Throw Exception in Thread");
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();
        thread.join();
        Assert.assertThat(ex.get().getMessage(), is("Throw Exception in Thread"));
    }

    @Test
    public void whenAddElementIntoCache() {
        CacheNonBlocking cache = new CacheNonBlocking();
        cache.add(model);
        assertThat(cache.add(model), is(model));
    }

    @Test
    public void whenDeleteElement() {
        CacheNonBlocking cache = new CacheNonBlocking();
        assertThat(cache.add(model), is(nullValue()));
        assertThat(cache.delete(model), is(model));
        assertThat(cache.add(model), is(nullValue()));
    }

    @Test
    public void whenUpdateElement() {
        CacheNonBlocking cache = new CacheNonBlocking();
        assertThat(cache.add(model), is(nullValue()));
        assertThat(cache.update(model).getVersion(), is(2));
    }

    @Test
    public void whenThrowExceptionAfterUpdateOldVersionModel() throws InterruptedException {
        CacheNonBlocking cache = new CacheNonBlocking();
        cache.add(model);
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread second = new Thread(
                () -> {
                    try {
                        cache.update(model);
                    } catch (OptimisticException e) {
                        ex.set(e);
                    }
                }
        );
        Thread first = new Thread(
                () -> {
                    try {
                        cache.update(new Base(1, 1));
                    } catch (OptimisticException e) {
                        ex.set(e);
                    }
                }
        );
        second.start();
        second.join();
        first.start();
        first.join();
        Assert.assertThat(ex.get().getMessage(), is("version not compute"));
    }
}
