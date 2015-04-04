package me.leolin.stock.util;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

/**
 * @author leolin
 */
public class FacebookCallbackWrapper<T> implements FacebookCallback<T> {

    public interface OnSuccessCallback<T> {
        void onSuccess(T t);
    }

    public interface OnCancelCallback {
        void onCancel();
    }

    public interface OnErrorCallback {
        void onError(FacebookException e);
    }

    private FacebookCallbackWrapper() {

    }

    public static <T> FacebookCallbackWrapper<T> create() {
        return new FacebookCallbackWrapper<T>();
    }

    private OnSuccessCallback onSuccessCallback;
    private OnCancelCallback onCancelCallback;
    private OnErrorCallback onErrorCallback;

    public FacebookCallbackWrapper doOnSuccess(OnSuccessCallback<T> onSuccessCallback) {
        this.onSuccessCallback = onSuccessCallback;
        return this;
    }

    public FacebookCallbackWrapper doOnError(OnErrorCallback onErrorCallback) {
        this.onErrorCallback = onErrorCallback;
        return this;
    }

    public FacebookCallbackWrapper doOnCancel(OnCancelCallback onCancelCallback) {
        this.onCancelCallback = onCancelCallback;
        return this;
    }

    @Override
    public void onSuccess(T t) {
        if (this.onSuccessCallback != null) {
            this.onSuccessCallback.onSuccess(t);
        }
    }

    @Override
    public void onCancel() {
        if (this.onCancelCallback != null) {
            this.onCancelCallback.onCancel();
        }
    }

    @Override
    public void onError(FacebookException e) {
        if (this.onErrorCallback != null) {
            this.onErrorCallback.onError(e);
        }
    }
}
