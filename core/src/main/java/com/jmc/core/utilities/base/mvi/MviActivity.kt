package com.jmc.core.utilities.base.mvi

import androidx.viewbinding.ViewBinding
import com.jmc.core.utilities.base.mvvm.MvvmActivity
import com.jmc.core.utilities.base.observeFlowStart

abstract class MviActivity<VB : ViewBinding, STATE, VM : MviViewModel<STATE, *>> :
    MvvmActivity<VB, VM>() {

    abstract fun renderViewState(viewState: STATE)

    override fun observeUi() {
        super.observeUi()
        observeFlowStart(viewModel.stateFlow, ::renderViewState)
    }
}
