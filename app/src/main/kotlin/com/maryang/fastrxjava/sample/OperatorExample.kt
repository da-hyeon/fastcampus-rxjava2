package com.maryang.fastrxjava.sample

import com.maryang.fastrxjava.entity.GithubRepo
import io.reactivex.Single

class OperatorExample {
    fun kotlinExample() {
        val number = 1
        val repo = GithubRepo()
        //자기 자신을 it으로 받음
        number.let {
            //it == 1
            it + 1
        }.let {
            //it == 2
        }

        //자기 자신을 it으로 받음
        repo.let {
            it.name
        }.run {

        }

        //블럭 자체가 자기 자신임
        //자기 자신을 리시버로 넘긴다
        repo.run {
            name
        }

        //인자값이 아닌 자기 자신을 반한함
        repo.also {
            it.name
        }.run {

        }

        repo.apply {
            name = "3"
        }.run {

        }

        with(number) {

        }

        fun collections() {
            val list = listOf(1, 2, 3)
            list.map {
                it + 2
            }
        }

        fun observable() {
            val list = listOf(true, true, true)
            Single.just(list)
                .map {
                    false
                }
        }
    }
}
