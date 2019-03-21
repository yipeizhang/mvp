package com.bull.mvp;

import android.os.Bundle;
import android.view.View;

public interface IView<P> {

    View bindView(View view);

    int provideLayoutId();

    P createPresenter();

    void init(Bundle savedInstanceState);
}
