package com.bull.mvp;

public interface IPresenter<V> {

    void attachView(V view);

    void detachView();

    V getView();

}
