package com.cwl.arccoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import androidx.lifecycle.*
import com.cwl.common.imageloader.ImageLoaderOptions
import com.cwl.common.imageloader.load
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    val tvm by lazy{ ViewModelProviders.of(this)[TestVM::class.java]}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        tvm.dowload()
        tvm.baidu()
//        tvm.login()
        var lv=MutableLiveData<String>()

        iv.load<ImageLoaderOptions>("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566055476665&di=168428c9ca223379ae9acfce0346b711&imgtype=0&src=http%3A%2F%2Fy3.ifengimg.com%2Fa%2F2016_03%2F6154e935f8a0fc6.jpg")

    }
}
