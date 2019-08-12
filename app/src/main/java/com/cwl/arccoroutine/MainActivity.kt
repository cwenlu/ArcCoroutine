package com.cwl.arccoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    val tvm by lazy{ ViewModelProviders.of(this)[TestVM::class.java]}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        tvm.dowload()
//        tvm.baidu()
        tvm.login()

    }
}
