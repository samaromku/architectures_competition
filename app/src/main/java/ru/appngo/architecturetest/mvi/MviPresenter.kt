package ru.appngo.architecturetest.mvi

import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.io
import ru.appngo.architecturetest.data.DataRepository

class MviPresenter(
    private val view: MviContract.View,
    private val repository: DataRepository
) : MviContract.Presenter {

    /**
     * Advantages:
     * All view states are presented in the contract.
     *
     */
    override fun onStart() {
        repository.sendRequest()
            .subscribeOn(io())
            .observeOn(mainThread())
            .doOnSubscribe { view.render(InProgress) }
            .subscribe({
                view.render(OnResponse(it))
            }, {
                view.render(OnError(it.message!!))
            })
    }
}
