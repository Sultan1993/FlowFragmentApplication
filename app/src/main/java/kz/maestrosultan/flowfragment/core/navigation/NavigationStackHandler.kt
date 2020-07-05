package kz.maestrosultan.flowfragment.core.navigation

import androidx.navigation.NavController
import java.util.*

data class NavigationStackItem(
    val controller: NavController,
    val isRootFlowController: Boolean
)

interface NavigationStackHandler {

    val navControllerStack: Stack<NavigationStackItem>
    val currentNavigationItem: NavigationStackItem?
        get() {
            return if (navControllerStack.isNotEmpty()) {
                navControllerStack.peek()
            } else {
                null
            }
        }

    fun pushNavigationController(item: NavigationStackItem)
    fun popNavigationController()
}