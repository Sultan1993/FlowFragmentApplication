package kz.maestrosultan.flowfragment.core.extensions

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.lang.Exception

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
        findNavController().popBackStack(destinationId, false)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Fragment.finishFlow() {
    try {
        val navHost = parentFragment
        val navContainer = navHost?.parentFragment

        if (navContainer is DialogFragment) {
            navContainer.dismiss()
        } else {
            findNavController().apply {
                popBackStack(graph.startDestination, false)
            }
            popBackStack()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}