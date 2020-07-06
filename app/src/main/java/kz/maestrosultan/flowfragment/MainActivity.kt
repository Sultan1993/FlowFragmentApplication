package kz.maestrosultan.flowfragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import kz.maestrosultan.flowfragment.core.TabBarManager
import kz.maestrosultan.flowfragment.core.navigation.NavigationStackHandler
import kz.maestrosultan.flowfragment.core.navigation.NavigationStackItem
import kz.maestrosultan.flowfragment.core.setUpNavigation
import kz.maestrosultan.flowfragment.dashboard.DashboardFlowFragment
import kz.maestrosultan.flowfragment.databinding.ActivityMainBinding
import kz.maestrosultan.flowfragment.home.HomeFlowFragment
import kz.maestrosultan.flowfragment.notifications.NotificationsFlowFragment
import java.util.*

class MainActivity : AppCompatActivity(), TabBarManager.NavHostProvider,
    TabBarManager.OnNavigationItemChanged, NavigationStackHandler {

    private lateinit var binding: ActivityMainBinding

    override val navControllerStack: Stack<NavigationStackItem> = Stack()

    private val bottomTabController by lazy {
        TabBarManager(supportFragmentManager, R.id.navHostContainer)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.bottomNavigation.setUpNavigation(bottomTabController)
        bottomTabController.setNavHostProvider(this)

        if (savedInstanceState == null) {
            bottomTabController.onNavigationItemSelected(R.id.navigation_home)
        }
    }

    override fun getNavHost(itemId: Int) = when(itemId) {
        R.id.navigation_dashboard -> DashboardFlowFragment()
        R.id.navigation_home -> HomeFlowFragment()
        R.id.navigation_notifications -> NotificationsFlowFragment()
        else -> null
    }

    override fun pushNavigationController(item: NavigationStackItem) {
        binding.bottomNavigation.post { binding.bottomNavigation.isVisible = item.isShowingBottomNavigation }

        if (navControllerStack.contains(item)) {
            navControllerStack.remove(item)
        }

        navControllerStack.push(item)
    }

    override fun popNavigationController() {
        if (navControllerStack.isNotEmpty()) {
            navControllerStack.pop()

            val isVisible = currentNavigationItem?.isShowingBottomNavigation ?: true
            binding.bottomNavigation.post { binding.bottomNavigation.isVisible = isVisible }
        }
    }

    override fun onBackPressed() {
        currentNavigationItem?.let {
            if (it.isRootFlowController) {
                if (!it.controller.navigateUp()) {
                    if (binding.bottomNavigation.selectedItemId == R.id.navigation_home) {
                        finish()
                    } else {
                        navControllerStack.clear()
                        bottomTabController.onNavigationItemSelected(R.id.navigation_home)
                    }
                }
            } else {
                if (!it.controller.navigateUp()) {
                    popNavigationController()
                    onBackPressed()
                }
            }
        } ?: finish()
    }

    override fun onTabItemChanged(itemId: Int) {
        // on tab item changed
    }

    override fun onTabItemReselected(itemId: Int) {
        currentNavigationItem?.let {
            if (!it.controller.navigateUp()) {
                if (!it.isRootFlowController) {
                    popNavigationController()
                }
            }
        }
    }
}