package kz.maestrosultan.flowfragment.core.extensions

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.popBackStack() {
    if (!findNavController().navigateUp()) {
        activity?.onBackPressed()
    }
}

fun Fragment.popToRoot() {
    findNavController().apply {
        popBackStack(graph.startDestination, false)
    }
}

fun Fragment.finishFlow() {
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
}