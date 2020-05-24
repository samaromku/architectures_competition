package ru.appngo.architecturetest.mvp

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_mvp.*
import ru.appngo.architecturetest.R
import ru.appngo.architecturetest.data.DataRepository
import ru.appngo.architecturetest.data.SomeTestData

class MvpActivity : AppCompatActivity(), MvpContract.View {

    private val dialog: ProgressDialog by lazy {
        ProgressDialog(this)
    }
    private val presenter = MvpPresenter(this, DataRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)
        presenter.onStart()
    }

    override fun showProgress() {
        dialog.show()
    }

    override fun hideProgress() {
        dialog.dismiss()
    }

    override fun showResult(someTestData: SomeTestData) {
        result_text.text = "result"
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}
