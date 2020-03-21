package com.maryang.fastrxjava.ui.repos

import com.maryang.fastrxjava.base.BaseViewModel
import com.maryang.fastrxjava.data.repository.GithubRepository
import com.maryang.fastrxjava.entity.GithubRepo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class GithubReposViewModel(
    //viewmodel이 Repository를 의존함
    private val repository: GithubRepository =
        GithubRepository()
) : BaseViewModel() {

    private val searchSubject = BehaviorSubject.createDefault("" to false)
    val loadingState = PublishSubject.create<Boolean>()
    val reposState = PublishSubject.create<List<GithubRepo>>()

    fun onCreate() {
        //rx에서 중요한것은 스트림이 연결되어야 한다.
        //로딩은 메인스레드에서 작업이 되어야 함
        compositeDisposable += searchSubject
            .debounce(400, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())  //메인스레드
            .doOnNext { loadingState.onNext(it.second) }    //로딩 시작
            .observeOn(Schedulers.io())     // 작업 스레드 변경
            .switchMapSingle {
                if (it.first.isEmpty()) Single.just(emptyList())
                else repository.searchGithubRepos(it.first) }       //
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext{loadingState.onNext(false)}
            .subscribe {
                reposState.onNext(it)
//                if (it.first.isEmpty()) return@subscribe
//                //로딩 시작
//                loadingState.onNext(it.second)
//                //서치 시작
//                repository.searchGithubRepos(it.first)
//                    .subscribe { repos ->
//                        //로딩 끝
//                        loadingState.onNext(false)
//                        reposState.onNext(repos)
//                    }
            }
    }

    fun searchGithubRepos(search: String) {
        searchSubject.onNext(search to true)
    }

    fun searchGithubRepos() {
        searchSubject.onNext(searchSubject.value!!.first to false)
    }
}
