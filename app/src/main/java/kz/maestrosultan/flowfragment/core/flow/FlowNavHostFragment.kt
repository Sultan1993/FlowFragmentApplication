package kz.maestrosultan.flowfragment.core.flow

import android.content.Context
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kz.maestrosultan.flowfragment.core.navigation.FlowVisibilityListener
import kz.maestrosultan.flowfragment.core.navigation.NavigationFlowFragment
import kz.maestrosultan.flowfragment.core.navigation.NavigationStackHandler
import kz.maestrosultan.flowfragment.core.navigation.NavigationStackItem

open class FlowNavHostFragment(
    private val isRootFlowController: Boolean = false
): NavHostFragment(), NavigationFlowFragment, FlowVisibilityListener {

    override var navStackHandler: NavigationStackHandler? = null

    private val navStackItem: NavigationStackItem
        get() {
            return if (parentFragment is DialogFragment) {
                NavigationStackItem(navController, true)
            } else {
                NavigationStackItem(navController, isRootFlowController)
            }
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is NavigationStackHandler) {
            navStackHandler = context
        }
    }

    override fun onCreateNavController(navController: NavController) {
        super.onCreateNavController(navController)
        pushControllerToStack()
    }

    override fun onFlowVisibleToUser() {
        pushControllerToStack()
    }

    private fun pushControllerToStack() {
        navStackHandler?.pushNavigationController(navStackItem)
    }
}