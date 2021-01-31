package com.mouhsinbourqaiba.firelogin

import com.mouhsinbourqaiba.firelogin.base.BaseViewModel
import com.mouhsinbourqaiba.firelogin.usecase.DetermineAuthStatus

class SplashViewModel(
    baseDependencies: Dependencies,
    private val determineAuthStatus: DetermineAuthStatus
    ): BaseViewModel(baseDependencies) {

        fun onScreenCreated() {
            subscription {
                determineAuthStatus
                    .invoke()!!
                    .subscribeOn(ioScheduler)
                    .observeOn(uiScheduler)
                    .subscribe({ authenficated ->
                        if(authenficated) {
                            emitCommand(OpenMainScreen)
                        }
                        else {
                            emitCommand(OpenEntryScreen)
                        }

                    }, {
                        emitCommand(ShowToast("Something is wrong"))
                    })
            }

        }


}