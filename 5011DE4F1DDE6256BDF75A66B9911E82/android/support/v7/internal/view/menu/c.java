package android.support.v7.internal.view.menu;

// compiled from: BaseWrapper.java
class c<T> {
    final T a;

    c(T r3_T) {
        if (r3_T == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        } else {
            this.a = r3_T;
        }
    }

    public T getWrappedObject() {
        return this.a;
    }
}