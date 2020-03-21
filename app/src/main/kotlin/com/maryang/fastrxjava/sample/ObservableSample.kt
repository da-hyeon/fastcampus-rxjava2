package com.maryang.fastrxjava.sample

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver

class ObservableSample {

    fun subscribesSample() {

        Observable.just(true) //observable 발행
            .subscribe(object : Observer<Boolean> { //observer 등록

                //
                override fun onComplete() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onSubscribe(d: Disposable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                //성공하고 아직 끝나지 않음.
                override fun onNext(t: Boolean) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onError(e: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })

        Single.just(true) //observable
            .subscribe(object : DisposableSingleObserver<Boolean>() {
                //onComplete도 발생
                override fun onSuccess(t: Boolean) {
                    //do something
                }

                override fun onError(e: Throwable) {

                }
            })

        Single.just(true)
            .subscribe() { onSuccess ->

            }

        Single.just(true)
            .subscribe({ onSuccess ->

            }, { onError ->

            })
    }

}