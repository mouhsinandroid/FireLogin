package com.mouhsinbourqaiba.firelogin

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.mouhsinbourqaiba.firelogin.base.BaseViewModel
import com.mouhsinbourqaiba.firelogin.usecase.DetermineAuthStatus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val application = module {
    single {
        androidContext().getSharedPreferences(
            "FireLoginPref", Context.MODE_MULTI_PROCESS
        )
    }

    single(IoScheduler) {
        Schedulers.io()
    }

    single(UiScheduler) {
        AndroidSchedulers.mainThread()
    }

    single {
        FirebaseAuth.getInstance()
    }

}

val useCasesModule = module {
    factory { DetermineAuthStatus( firebaseAuth = get())}

}
val viewModelModule = module {

    single {
        BaseViewModel.Dependencies(
            ioScheduler = get(IoScheduler),
            uiScheduler = get(UiScheduler)
        )
    }
    single {
        SplashViewModel(baseDependencies = get(), determineAuthStatus = get())
    }
}