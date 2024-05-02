package com.jmc.core.utilities.base.mvi


import com.jmc.core.utilities.base.flow.MutableEventFlow
import com.jmc.core.utilities.base.flow.asEventFlow
import com.jmc.core.utilities.base.mvvm.MvvmViewModel


abstract class MviViewModel<STATE, EVENT> : MvvmViewModel() {

    private val _stateFlow = MutableEventFlow<STATE>()
    val stateFlow = _stateFlow.asEventFlow()

    abstract fun onTriggerEvent(eventType: EVENT)

    protected fun setState(state: STATE) = safeLaunch {
        _stateFlow.emit(state)
    }

}
