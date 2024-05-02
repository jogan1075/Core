
package com.jmc.core.utilities.base.mvi

import androidx.viewbinding.ViewBinding
import com.jmc.core.utilities.base.mvvm.MvvmFragment
import com.jmc.core.utilities.base.observeFlowStart

abstract class MviFragment<VB : ViewBinding, STATE, VM : MviViewModel<STATE, *>> :
    MvvmFragment<VB, VM>() {

    abstract fun renderViewState(viewState: STATE)

    override fun observeUi() {
        super.observeUi()
        observeFlowStart(viewModel.stateFlow, ::renderViewState)
    }
}
