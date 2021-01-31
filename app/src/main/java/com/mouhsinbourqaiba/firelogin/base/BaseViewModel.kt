package com.mouhsinbourqaiba.firelogin.base

import androidx.lifecycle.ViewModel
import com.mouhsinbourqaiba.firelogin.VMCommand
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.IoScheduler
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

open class BaseViewModel(dependencies: Dependencies): ViewModel() {

    private val subscriptions = CompositeDisposable()

    protected val ioScheduler = dependencies.ioScheduler
    protected val uiScheduler = dependencies.uiScheduler
    
    private val commandsSubject: Subject<VMCommand> = PublishSubject.create()
    val command: Observable<VMCommand> = commandsSubject.hide()

    protected fun emitCommand(command: VMCommand) {
        commandsSubject.onNext(command)
    }

    protected fun subscription(block:() -> Disposable) {
        subscriptions.add(block())
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }

    data class Dependencies(
        val ioScheduler: IoScheduler,
        val uiScheduler: IoScheduler
    )
}