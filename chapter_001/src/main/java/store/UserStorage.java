package store;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("userMap")
    private final Map<Integer, User> userMap = new HashMap<>();

    public boolean add(User user) {
        boolean rsl = false;
        synchronized (userMap) {
            if (userMap.putIfAbsent(user.getId(), user) == null) {
                rsl = true;
            }
        }
        return rsl;
    }

    public boolean update(User user) {
        boolean rsl = true;
        synchronized (userMap) {
            if (!userMap.containsKey(user.getId())) {
                rsl = false;
            } else {
                userMap.put(user.getId(), user);
            }
        }
        return rsl;
    }

    public boolean delete(User user) {
        boolean rsl = false;
        synchronized (userMap) {
            if (userMap.containsKey(user.getId())) {
                userMap.remove(user.getId());
                rsl = true;
            }
        }
        return rsl;
    }

    public boolean transfer(int fromId, int toId, int amount) {
        boolean rsl = false;
        synchronized (userMap) {
            if (userMap.containsKey(fromId) && userMap.containsKey(toId) && userMap.get(fromId).getAmount() >= amount) {
                User userFromId = userMap.get(fromId);
                User userToId = userMap.get(toId);
                userFromId.setAmount(userFromId.getAmount() - amount);
                userToId.setAmount(userToId.getAmount() + amount);
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        synchronized (userMap) {
            for (Map.Entry<Integer, User> pair : userMap.entrySet()
            ) {
                sb.append(pair.getValue());
            }
        }
        return sb.toString();
    }
}
