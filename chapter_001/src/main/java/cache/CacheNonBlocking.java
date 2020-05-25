package cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * кеш для хранение моделей. в кеше должны быть методы
 * add(Base model), update(Base model) delete(Base model),
 * <p>
 * 2. Для хранения данных в кеше использовать ConcurrentHashMap<Integer, Base>.
 * <p>
 * В качестве ключа используйте int id. в качестве значения Base модель
 */

public class CacheNonBlocking {
    private final ConcurrentHashMap<Integer, Base> map;

    public CacheNonBlocking() {
        this.map = new ConcurrentHashMap<>();
    }

    public Base add(Base model) {
        return map.put(model.getId(), model);
    }

    /**
     * public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
     * Если нам необходимо произвести какое-то действие со значением в Map, если оно там есть
     * <p>
     * Если значение для указанного ключа присутствует, делаем тогда
     * попытку вычислить новое сопоставление с учетом ключа и его текущего сопоставленного значения.
     * Весь вызов метода выполняется атомарно.
     * Некоторые попытки обновления на этой карте другими потоками могут быть заблокированы во время вычислений,
     * поэтому вычисления должны быть короткими и простыми и не должны пытаться обновить
     * какие-либо другие сопоставления этой карты.
     */
    public Base update(Base model) {
        return map.computeIfPresent(model.getId(), (k, v) -> {
            if (v.getVersion() != model.getVersion()) {
                throw new OptimisticException("version not compute");
            }
            model.setVersion(model.getVersion() + 1);
            return model;
        });
    }

    public Base delete(Base model) {
        return map.remove(model.getId());
    }
}
