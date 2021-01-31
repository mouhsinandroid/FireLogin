package com.mouhsinbourqaiba.firelogin

import android.os.Bundle
import com.mouhsinbourqaiba.firelogin.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SplashScreenActivity:BaseActivity<SplashViewModel>() {
    override val viewModel: SplashViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onScreenCreated()
    }

    override fun handleVMCommand(command: VMCommand)= when(command) {
        is OpenEntryScreen -> {
            // Open the entry Screen
             true
        }

        is OpenMainScreen -> {
            // Open the Main Screen
            openMainActivity()
            true
        }
        else -> super.handleVMCommand(command)
    }

    private fun openMainActivity() {
        MainActivity.start(this)
        finish()
    }
}