package com.bull.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public abstract class XActivity<P extends IPresenter> extends FragmentActivity implements IView<P> {

    /**
     * 这里的P类型将根据实际XActivity的继承者来指定，用来关联具体的Presenter
     */
    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (provideLayoutId() > 0){
            setContentView(bindView(View.inflate(this, provideLayoutId(), null)));
            init(savedInstanceState);
        } else {
            throw new NullPointerException("layout id can not be null");
        }
    }

    @Override
    public View bindView(View view) {
        return view;
    }

    @Override
    public void init(Bundle savedInstanceState) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.detachView();
        }
    }

    /**
     * 获取关联的Presenter
     * @return
     */
    protected P getPresenter(){
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        if (mPresenter != null){
            mPresenter.attachView(this);
        }
        return mPresenter;
    }
}
