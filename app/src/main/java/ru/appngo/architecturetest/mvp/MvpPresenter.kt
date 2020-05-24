package ru.appngo.architecturetest.mvp

import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.io
import ru.appngo.architecturetest.data.DataRepository

class MvpPresenter(
    private val view: MvpContract.View,
    private val repository: DataRepository
):MvpContract.Presenter {

    override fun onStart() {
        repository.sendRequest()
            .subscribeOn(io())
            .observeOn(mainThread())
            .doOnSubscribe { view.showProgress() }
            .doAfterTerminate { view.hideProgress() }
            .subscribe({
                view.showResult(it)
            }, {
                view.showError("error")
            })
        // show progress indication
        // send request
        // hide progress indication
        // show response
        // show error
    }
}
