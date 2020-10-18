package com.jmc.core.coroutines

import com.jmc.core.extensions.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class CompletableEmptyUseCase(
    override val backgroundContext: CoroutineContext,
    override val foregroundContext: CoroutineContext
) : BaseUnitUseCase<LiveCompletable>(
    backgroundContext, foregroundContext
) {
    protected abstract suspend fun executeOnBackground()

    override fun execute(liveData: LiveCompletable) {
        CoroutineScope(foregroundContext + newJob()).launch {
            liveData.postLoading()

            runCatching {
                withContext(backgroundContext) { executeOnBackground() }
            }.onSuccess {
                liveData.postComplete()
            }.onFailure { throwable ->
                when (throwable) {
                    is CancellationException -> liveData.postCancel()
                    else -> liveData.postThrowable(throwable)
                }
            }
        }
    }
}