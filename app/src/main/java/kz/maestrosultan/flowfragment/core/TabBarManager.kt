package kz.maestrosultan.flowfragment.core

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kz.maestrosultan.flowfragment.core.flow.FlowNavHostFragment

class TabBarManager(
    private val fragmentManager: FragmentManager,
    private val containerId: Int,
    private val onNavigationItemChanged: OnNavigationItemChanged? = null
) {
    private var navHostProvider: NavHostProvider? = null

    interface OnNavigationItemChanged {
        fun onTabItemChanged(itemId: Int)
        fun onTabItemReselected(itemId: Int)
    }

    interface NavHostProvider {
        fun getNavHost(itemId: Int): Fragment?
    }

    fun setNavHostProvider(provider: NavHostProvider) {
        navHostProvider = provider
    }

    fun onNavigationItemSelected(itemId: Int): Boolean {
        val fragment = fragmentManager.findFragmentByTag(itemId.toString())
            ?: navHostProvider?.getNavHost(itemId)
            ?: return false

        val transaction = fragmentManager
            .beginTransaction()

        if (fragmentManager.fragments.isNullOrEmpty()) {
            transaction
                .replace(containerId, fragment, itemId.toString())
                .addToBackStack(itemId.toString())
                .commit()
        } else {
            fragmentManager.fragments.firstOrNull { it.isVisible }?.let { onBecameInvisible(it) }
            fragmentManager.fragments.forEach { transaction.hide(it) }

            if (fragment.isAdded) {
                transaction.show(fragment)
                transaction.commit()
                onBecameVisible(fragment)
            } else {
                transaction.add(containerId, fragment, itemId.toString())
                transaction.commit()
            }
        }

        onNavigationItemChanged?.onTabItemChanged(itemId)
        return true
    }

    fun onNavigationItemReselected(item: MenuItem) {
        onNavigationItemChanged?.onTabItemReselected(item.itemId)
    }

    private fun onBecameVisible(fragment: Fragment) {
        notifyFlowVisible(fragment)
    }

    private fun onBecameInvisible(fragment: Fragment) {

    }

    private fun notifyFlowVisible(fragment: Fragment) {
        fragment.childFragmentManager.fragments.forEach {
            if (it is FlowNavHostFragment) {
                it.onFlowVisibleToUser()
            }

            notifyFlowVisible(it)
        }
    }
}

fun BottomNavigationView.setUpNavigation(bottomNavController: TabBarManager) {
    setOnNavigationItemSelectedListener {
        bottomNavController.onNavigationItemSelected(it.itemId)
    }
    setOnNavigationItemReselectedListener {
        bottomNavController.onNavigationItemReselected(it)
    }
}