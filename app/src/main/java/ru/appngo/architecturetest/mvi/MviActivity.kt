package ru.appngo.architecturetest.mvi

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.appngo.architecturetest.R
import ru.appngo.architecturetest.data.DataRepository
import ru.appngo.architecturetest.data.SomeTestData

class MviActivity : AppCompatActivity(), MviContract.View {

    private val dialog: ProgressDialog by lazy {
        ProgressDialog(this)
    }
    private val presenter = MviPresenter(this, DataRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onStart()
    }

    override fun render(state: MviViewState) {
        when (state) {
            InProgress -> showProgress()
            is OnError -> showError(state.error)
            is OnResponse -> showResult(state.someTestData)
        }
    }

    private fun showProgress() {
        dialog.show()
    }

    private fun hideProgress() {
        dialog.dismiss()
    }

    private fun showResult(someTestData: SomeTestData) {
        hideProgress()
        result_text.text = "result"
    }

    private fun showError(error: String) {
        hideProgress()
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}
