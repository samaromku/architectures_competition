package ru.appngo.architecturetest.data

import io.reactivex.Single

class DataRepository {

    fun sendRequest(): Single<SomeTestData> {
        return Single.just(SomeTestData())
            .map {
                Thread.sleep(3000)
                return@map it
            }
    }
}

class SomeTestData
