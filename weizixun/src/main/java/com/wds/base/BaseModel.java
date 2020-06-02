package com.wds.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseModel {
    private CompositeDisposable compositeDisposable;
    public void onDestroy(){
        if (compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }
    public void addModel(Disposable disposable){
        if (compositeDisposable==null){
            compositeDisposable=new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
}
