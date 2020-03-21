package com.maryang.fastrxjava.sample

import com.maryang.fastrxjava.entity.GithubRepo

class FunctionalSample  {
    val list = listOf(1,2,3)

    //명령형
    //리스트를 순회하면서 하나씩 더해라
    fun sum() : Int {

        var result : Int = 0
        list.forEach {
            result += it
        }
        return result
    }

    //함수형
    //순회하면 이렇게 되어있을거야
    fun sumFunctional() : Int{

        //accumulate -> result에 해당 하는 값
        //i -> 리스트의 값
        return list.fold(0) { accumulate, i->
            //어쩔 수 없이 명령을 해야 함
            accumulate + i
        }
    }

    //명령형
    fun starRepoNames(repos : List<GithubRepo>) : List<String>{
        val names = mutableListOf<String>()
        repos.forEach {
            if (it.star) {
                names.add(it.name)
            }
        }
        return names
    }

    //함수형
    fun starRepoNamesFunctional(repos : List<GithubRepo>) : List<String> {
        return repos.filter { it.star }
            .map { it.name }
    }

}