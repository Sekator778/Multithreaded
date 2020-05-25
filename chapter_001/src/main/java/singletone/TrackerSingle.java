package singletone;

import cache.Base;

/**
 * При реализации шаблон Singleton необходимо учитывать проблему с visibility (Видимостью).
 *
 * Эту проблему можно решать двумя способами
 *
 * используя volatile или сразу публикуя объект через final.
 *
 * Многопоточные реализации аналогичный не многопоточным.
 */
public final class TrackerSingle {
    private TrackerSingle() {
    }

    public static TrackerSingle getInstance() {
        return Holder.INSTANCE;
    }

    public Base add(Base model) {
        return model;
    }

    private static final class Holder {
        private static final TrackerSingle INSTANCE = new TrackerSingle();
    }

    public static void main(String[] args) {
        TrackerSingle single = TrackerSingle.getInstance();
    }
}