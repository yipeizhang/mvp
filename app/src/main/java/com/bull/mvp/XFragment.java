package com.bull.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class XFragment<P extends IPresenter> extends Fragment implements IView<P>{

    /**
     * 这里的P类型将根据实际XFragment的继承者来指定，用来关联具体的Presenter
     */
    private P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (provideLayoutId() > 0){
            return bindView(inflater.inflate(provideLayoutId(), null));
        }
        throw new NullPointerException("layout id can not be null");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init(savedInstanceState);
    }

    @Override
    public View bindView(View view) {
        return view;
    }

    @Override
    public void init(Bundle savedInstanceState) {
    }

    protected P getPresenter(){
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        if (mPresenter != null){
            mPresenter.attachView(this);
        }
        return mPresenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null){
            mPresenter.detachView();
        }
    }
}
