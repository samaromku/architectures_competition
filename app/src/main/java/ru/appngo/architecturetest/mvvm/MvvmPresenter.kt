package ru.appngo.architecturetest.mvvm

import androidx.lifecycle.MutableLiveData
import io.reactivex.schedulers.Schedulers.io
import ru.appngo.architecturetest.data.DataRepository

class MvvmPresenter(
    private val liveData: MutableLiveData<MvvmViewModel>,
    private val model: MvvmViewModel,
    private val repository: DataRepository
) : MvvmContract.Presenter {

    /**
     * Advantages:
     * don't have to switch to main thread, as in library there is annotation @MainThread
     * ViewModel survives on lifeCycle changes
     *
     * Disadvantages:
     *
     *
     * Has opposite pros and cons:
     * View can be controlled by states. However, with time there will be a lot of them and they need to be combined
     */
    override fun onStart() {
        if (model.state !is OnCreate) {
            return
        }
        repository.sendRequest()
            .subscribeOn(io())
            .doOnSubscribe { updateLiveData(InProgress) }
            .subscribe({
                updateLiveData(OnResponse(it))
            }, {
                updateLiveData(OnError("error"))
            })
    }

    private fun updateLiveData(viewState: ViewState) {
        liveData.postValue(model.apply {
            state = viewState
        })
    }
}
