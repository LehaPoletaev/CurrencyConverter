package com.e.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.e.myapplication.databinding.ActivityMainBinding
import com.e.myapplication.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {

    lateinit var  mainViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
            .apply { this.setLifecycleOwner(this@MainActivity)
                    this.viewmodel = mainViewModel}

    }


}


