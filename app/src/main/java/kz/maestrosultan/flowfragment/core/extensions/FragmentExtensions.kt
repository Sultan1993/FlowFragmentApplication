package kz.maestrosultan.flowfragment.core.extensions

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.contains
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun Fragment.popBackStack() {
    try {
        if (!findNavController().navigateUp()) {
            activity?.onBackPressed()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Fragment.popToRoot() {
    try {
        findNavController().apply {
            popBackStack(graph.startDestination, false)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Fragment.popToDestination(destinationId: Int) {
    try {
        if (findNavController().graph.contains(destinationId)) {
            findNavController().popBackStack(destinationId, false)
        } else {
            finishFlow()
            lifecycleScope.launch {
                delay(500) //small delay to allow transition to end
                popToDestination(destinationId)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Fragment.finishFlow() {
    try {
        val navHost = parentFragment
        val navContainer = navHost?.parentFragment

        if (navContainer is DialogFragment) {
            navContainer.dismissAllowingStateLoss()
        } else {
            val hasNestedFlows = childFragmentManager.fragments.any { it is NavHostFragment }

            if (hasNestedFlows) {
                childFragmentManager.fragments.forEach {
                    if (it is NavHostFragment) {
                        it.finishFlow()
                    }
                }
            } else {
                findNavController().apply {
                    popBackStack(graph.startDestination, false)
                }
                popBackStack()
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}