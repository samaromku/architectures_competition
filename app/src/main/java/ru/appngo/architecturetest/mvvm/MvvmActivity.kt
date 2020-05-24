package ru.appngo.architecturetest.mvvm

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_mvvm.*
import ru.appngo.architecturetest.R
import ru.appngo.architecturetest.data.DataRepository

class MvvmActivity : AppCompatActivity() {

    private lateinit var presenter: MvvmContract.Presenter
    private val dialog: ProgressDialog by lazy {
        ProgressDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)


        val model = ViewModelProvider(this).get(MvvmViewModel::class.java)
        val liveData = MutableLiveData(model).apply {
            observe(this@MvvmActivity, Observer {
                when (val state = it.state) {
                    InProgress -> {
                        dialog.show()
                    }
                    is OnError -> {
                        dialog.dismiss()
                        Toast.makeText(this@MvvmActivity, state.error, Toast.LENGTH_SHORT).show()
                    }
                    is OnResponse -> {
                        dialog.dismiss()
                        result_text.text = "result_mvvm"
                    }
                }
            })
        }
        presenter = MvvmPresenter(liveData, model, DataRepository())
        presenter.onStart()
    }
}
