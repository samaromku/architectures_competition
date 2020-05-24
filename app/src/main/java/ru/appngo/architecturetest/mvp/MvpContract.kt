package ru.appngo.architecturetest.mvp

import ru.appngo.architecturetest.data.SomeTestData

interface MvpContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showResult(someTestData: SomeTestData)
        fun showError(error: String)
    }

    interface Presenter {
        fun onStart()
    }
}
