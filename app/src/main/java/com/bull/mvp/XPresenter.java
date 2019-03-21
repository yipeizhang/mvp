package com.bull.mvp;

import java.lang.ref.WeakReference;

public class XPresenter<V extends IView> implements IPresenter<V> {

    /**
     * 这里的V类型将根据实际XPresenter的继承者来指定，用来关联具体的Activity或Fragment
     */
    private WeakReference<V> mViewRef;

    @Override
    public void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (mViewRef != null && mViewRef.get() != null) {
            mViewRef.clear();
        }
        mViewRef = null;
    }

    @Override
    public V getView() {
        if (mViewRef != null){
            return mViewRef.get();
        }
        return null;
    }

}
