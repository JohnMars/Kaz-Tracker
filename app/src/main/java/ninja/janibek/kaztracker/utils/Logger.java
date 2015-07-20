package ninja.janibek.kaztracker.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для логирования.
 * <p/>
 * <p>Использовать {@link #v(String, String)}, {@link #d(String, String)},
 * {@link #i(String, String)}, {@link #w(String, String)},
 * {@link #e(String, String)} и {@link #wtf(String, String)} методы.
 * <p/>
 * <p>Приоритеты логирования {@link Level#OFF}, {@link Level#ASSERT}, {@link Level#ERROR},
 * {@link Level#WARNING}, {@link Level#INFO}, {@link Level#DEBUG}, {@link Level#VERBOSE}
 * Логи с приоритетом {@link Level#VERBOSE} не должны быть включены в компиляцию
 * за исключения тестового билда. Приоритеты идут сверху вниз.
 * <p/>
 * <p><b>Tip:</b> Объявить <code>TAG</code> константу в классе. Для отформатированного <code>TAG</code>
 * использовать {@link #makeLogTag(String)} или {@link #makeLogTag(Class)} методы.
 * <p/>
 * <pre>private static final String TAG = {@link #makeLogTag(Class)}</pre>
 * <p/>
 * Все вышеперечисленные методы для вывода логов создают StringBuilder. Если использовать слишком много логов, это может
 * повлиять на производительность приложения, а так же на закэшированный объем памяти.
 * Предпочтительно отключать все логи для релиз билдов {@link #setLevel(Level)}</p>
 * <p/>
 * Created by Galymzhan Sh on 4/15/15.
 */
public class Logger {
    public static final String LOG_PREFIX = "kt_";
    public static final String LOG_PREFIX_SDK = "kt-sdk_";

    @SuppressWarnings("unused")
    private static final String TAG = makeLogTag("Logger", LOG_PREFIX_SDK);
    private static final int LOG_TAG_LENGTH_MAX = 24;

    /**
     * Приоритеты для вывода логов
     * <li>{@link #VERBOSE}</li>
     * <li>{@link #DEBUG}</li>
     * <li>{@link #INFO}</li>
     * <li>{@link #WARNING}</li>
     * <li>{@link #ERROR}</li>
     * <li>{@link #ASSERT}</li>
     * <li>{@link #OFF}</li>
     */
    public enum Level {
        /**
         * VERBOSE приоритет для вывода лога.
         * Использовать с {@link #v(String, String)} или {@link #v(String, String, Throwable)}
         */
        VERBOSE,

        /**
         * DEBUG приоритет для вывода отладочных логов.
         * Использовать с {@link #d(String, String)} или {@link #d(String, String, Throwable)}
         */
        DEBUG,

        /**
         * INFO приоритет для вывода информационных логов.
         * Использовать с {@link #i(String, String)} или {@link #i(String, String, Throwable)}
         */
        INFO,

        /**
         * WARNING приоритет для вывода логов с предупреждениями но не критичных.
         * Использовать с {@link #w(String, String)} или {@link #w(String, String, Throwable)}
         */
        WARNING,

        /**
         * ERROR приоритет для вывода лога с ошибками.
         * Использовать с {@link #e(String, String)} или {@link #e(String, String, Throwable)}
         */
        ERROR,

        /**
         * ASSERT самый высокий приоритет для вывода критических логов.
         * Использовать с {@link #wtf(String, String)} или {@link #wtf(String, String, Throwable)}
         */
        ASSERT,

        /**
         * OFF Приоритет для отключения лога.
         * Все логи отправленные для вывода будут игнорированы.
         */
        OFF
    }

    /**
     * Приоритет для показа логов. Вызывать {@link #setLevel(Level)} для установки приоритета
     * По умолчанию установлен {@link Level#OFF}
     */
    private static Level sLogLevel = Level.OFF;

    /**
     * Возвращает отформатированый тэг. Формат тэга <i>префикс_<b>тэг_из_параметра</b></i>.
     * Если длина отформатированного тэга больше {@link #LOG_TAG_LENGTH_MAX}, то тэг stripped out
     * down to {@link #LOG_TAG_LENGTH_MAX}
     *
     * @param tag Тэг, который нужно отформатировать.
     * @param prefix Префикс к тэгу
     * @return Возвращает отформатированный тэг
     */
    public static String makeLogTag(@NonNull String tag, @NonNull String prefix) {
        final int prefixLength = prefix.length();
        if (tag.length() > LOG_TAG_LENGTH_MAX - prefixLength) {
            return prefix + tag.substring(0, LOG_TAG_LENGTH_MAX - prefixLength - 1);
        }

        return prefix + tag;
    }

    /**
     * Возвращает отформатированый тэг. Формат тэга <i>префикс_<b>тэг_из_параметра</b></i>.
     * Если длина отформатированного тэга больше {@link #LOG_TAG_LENGTH_MAX}, то тэг stripped out
     * down to {@link #LOG_TAG_LENGTH_MAX}
     *
     * @param tag Тэг, который нужно отформатировать.
     * @return Возвращает отформатированный тэг
     */
    @NonNull
    public static String makeLogTag(@NonNull String tag) {
        return makeLogTag(tag, LOG_PREFIX);
    }

    /**
     * !!! Предупреждение: код обфускаторы могут изменить название класса
     * Возвращает отформатированый тэг. Формат тэга <i>префикс_<b>название_класса_из_параметра</b></i>.
     * Если длина отформатированного тэга больше {@link #LOG_TAG_LENGTH_MAX}, то тэг stripped out
     * down to {@link #LOG_TAG_LENGTH_MAX}
     *
     * @param cls Класс, из которого нужно создать тэг.
     * @return Возвращает отформатированный тэг
     */
    @NonNull
    @Deprecated
    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

    /**
     * Установка приоритета для вывода логов. {@link Level#OFF} для отключения
     *
     * @param level Приоритет для установки. См. {@link Level}
     */
    public static synchronized void setLevel(Level level) {
        sLogLevel = level;

        // output the priority actions
        if (level == Level.OFF) {
            i(TAG, "Logger is shutting down. No more log outputs will be printed.");
        } else {
            i(TAG, "Logger priority has changed to " + level.name());
        }
    }

    /**
     * Вывод лога с {@link Level#VERBOSE} приоритетом.
     *
     * @param tag Тэг для фильтрации
     * @param msg Сообщение для вывода.
     */
    public static void v(@NonNull String tag, @NonNull String msg) {
        v(tag, msg, null);
    }

    /**
     * Вывод лога и исключения с {@link Level#VERBOSE} приоритетом.
     *
     * @param tag   Тэг для фильтрации
     * @param msg   Сообщение для вывода.
     * @param cause Исключение для вывода с трассировкой стека
     */
    public static void v(@NonNull String tag, @NonNull String msg, @Nullable Throwable cause) {
        if (isLoggingEnabled(Level.VERBOSE)) {
            Log.v(tag, msg, cause);
        }
    }

    /**
     * Вывод лога с {@link Level#DEBUG} приоритетом.
     *
     * @param tag Тэг для фильтрации
     * @param msg Сообщение для вывода.
     */
    public static void d(@NonNull String tag, @NonNull String msg) {
        if (isLoggingEnabled(Level.DEBUG)) {
            Log.d(tag, msg);
        }
    }

    /**
     * Вывод лога и исключения с {@link Level#DEBUG} приоритетом.
     *
     * @param tag   Тэг для фильтрации
     * @param msg   Сообщение для вывода.
     * @param cause Исключение для вывода с трассировкой стека
     */
    public static void d(@NonNull String tag, @NonNull String msg, @Nullable Throwable cause) {
        if (isLoggingEnabled(Level.DEBUG)) {
            Log.d(tag, msg, cause);
        }
    }

    /**
     * Вывод лога с {@link Level#INFO} приоритетом.
     *
     * @param tag Тэг для фильтрации
     * @param msg Сообщение для вывода.
     */
    public static void i(@NonNull String tag, @NonNull String msg) {
        if (isLoggingEnabled(Level.INFO)) {
            Log.i(tag, msg);
        }
    }

    /**
     * Вывод лога и исключения с {@link Level#INFO} приоритетом.
     *
     * @param tag   Тэг для фильтрации
     * @param msg   Сообщение для вывода.
     * @param cause Исключение для вывода с трассировкой стека
     */
    public static void i(@NonNull String tag, @NonNull String msg, @Nullable Throwable cause) {
        if (isLoggingEnabled(Level.INFO)) {
            Log.i(tag, msg, cause);
        }
    }

    /**
     * Вывод лога с {@link Level#WARNING} приоритетом.
     *
     * @param tag Тэг для фильтрации
     * @param msg Сообщение для вывода.
     */
    public static void w(@NonNull String tag, @NonNull String msg) {
        if (isLoggingEnabled(Level.WARNING)) {
            Log.w(tag, msg);
        }
    }

    /**
     * Вывод лога и исключения с {@link Level#WARNING} приоритетом.
     *
     * @param tag   Тэг для фильтрации
     * @param msg   Сообщение для вывода.
     * @param cause Исключение для вывода с трассировкой стека
     */
    public static void w(@NonNull String tag, @NonNull String msg, @Nullable Throwable cause) {
        if (isLoggingEnabled(Level.WARNING)) {
            Log.w(tag, msg, cause);
        }
    }

    /**
     * Вывод лога с {@link Level#ERROR} приоритетом.
     *
     * @param tag Тэг для фильтрации
     * @param msg Сообщение для вывода.
     */
    public static void e(@NonNull String tag, @NonNull String msg) {
        if (isLoggingEnabled(Level.ERROR)) {
            Log.e(tag, msg);
        }
    }

    /**
     * Вывод лога и исключения с {@link Level#ERROR} приоритетом.
     *
     * @param tag   Тэг для фильтрации
     * @param msg   Сообщение для вывода.
     * @param cause Исключение для вывода с трассировкой стека
     */
    public static void e(@NonNull String tag, @NonNull String msg, @Nullable Throwable cause) {
        if (isLoggingEnabled(Level.ERROR)) {
            Log.e(tag, msg, cause);
        }
    }

    /**
     * Вывод лога с {@link Level#ASSERT} приоритетом.
     *
     * @param tag Тэг для фильтрации
     * @param msg Сообщение для вывода.
     */
    @TargetApi(Build.VERSION_CODES.FROYO)
    public static void wtf(@NonNull String tag, @NonNull String msg) {
        if (isLoggingEnabled(Level.ASSERT)) {
            Log.wtf(tag, msg);
        }
    }

    /**
     * Вывод лога и исключения с {@link Level#ASSERT} приоритетом.
     *
     * @param tag   Тэг для фильтрации
     * @param msg   Сообщение для вывода.
     * @param cause Исключение для вывода с трассировкой стека
     */
    @TargetApi(Build.VERSION_CODES.FROYO)
    public static void wtf(@NonNull String tag, @NonNull String msg, @Nullable Throwable cause) {
        if (isLoggingEnabled(Level.ASSERT)) {
            Log.wtf(tag, msg, cause);
        }
    }

    /**
     * Проверка на логирование.
     *
     * @param level Уроверень приоритета для проверки
     */
    private static boolean isLoggingEnabled(final Level level) {
        boolean isEnabled = false;
        if (level.compareTo(sLogLevel) >= 0) {
            isEnabled = true;
        }

        return isEnabled;
    }

    private Logger() {
        throw new IllegalStateException(Logger.class.getSimpleName()
                + " class should never have an instance.");
    }

    public static class Queue {
        private final List<Marker> mMarkers = new ArrayList<>();
        private boolean mFinished = false;
        private final String mTag;

        public Queue(String tag) {
            mTag = tag;
        }

        public static boolean isEnabled() {
            return isLoggingEnabled(Level.DEBUG);
        }

        public synchronized void add(String name, String threadName) {
            if (mFinished) {
                throw new IllegalStateException("Marker added to finished Logger.Queue");
            }

            mMarkers.add(new Marker(name, threadName, SystemClock.elapsedRealtime()));
        }

        public synchronized void finish(String header) {
            long duration = getTotalDuration();
            if (duration <= 0) {
                return;
            }

            d(mTag, String.format("(%-4d ms) %s", duration, header));

            long prevTime = mMarkers.get(0).time;
            for (Marker marker : mMarkers) {
                long time = marker.time;
                d(mTag, String.format("(+%-4d) [%s] %s", time - prevTime,
                        marker.threadName, marker.event));
                prevTime = time;
            }

            mFinished = true;
        }

        @Override
        protected void finalize() throws Throwable {
            if (!mFinished) {
                finish("Logger.Queue on the loose");
                e(mTag, "Marker log finalized without finish() - uncaught exit point");
            }

            super.finalize();
        }

        private long getTotalDuration() {
            if (mMarkers.size() == 0) {
                return 0l;
            } else if (mMarkers.size() == 1) {
                return mMarkers.get(0).time;
            }

            return mMarkers.get(mMarkers.size() - 1).time
                    - mMarkers.get(0).time;
        }

        private static class Marker {
            final String event;
            final String threadName;
            final long time;

            Marker(String event, String threadName, long time) {
                this.event = event;
                this.threadName = threadName;
                this.time = time;
            }
        }
    }
}