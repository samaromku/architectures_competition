package ru.appngo.architecturetest.mvi

import ru.appngo.architecturetest.data.SomeTestData

interface MviContract {
    interface View {
        fun render(state: MviViewState)
    }

    interface Presenter {
        fun onStart()
    }
}

sealed class MviViewState

object InProgress : MviViewState()

data class OnError(
    val error: String
) : MviViewState()

data class OnResponse(
    val someTestData: SomeTestData
) : MviViewState()


