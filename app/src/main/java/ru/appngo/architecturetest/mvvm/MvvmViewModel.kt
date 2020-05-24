package ru.appngo.architecturetest.mvvm

import androidx.lifecycle.ViewModel
import ru.appngo.architecturetest.data.SomeTestData

class MvvmViewModel : ViewModel() {
    var state: ViewState = OnCreate
}

sealed class ViewState

object OnCreate : ViewState()

object InProgress : ViewState()

data class OnError(
    val error: String
) : ViewState()

data class OnResponse(
    val someTestData: SomeTestData
) : ViewState()
