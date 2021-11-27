package async.blocking.callback;

@FunctionalInterface
public interface Callback<T> {

    abstract void callback(Throwable error,T result);
}