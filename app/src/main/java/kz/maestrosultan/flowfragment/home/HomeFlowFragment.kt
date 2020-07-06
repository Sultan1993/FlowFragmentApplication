package kz.maestrosultan.flowfragment.home

import kz.maestrosultan.flowfragment.R
import kz.maestrosultan.flowfragment.core.flow.FlowContainerFragment
import kz.maestrosultan.flowfragment.core.flow.FlowNavHostFragment

class HomeFlowFragment: FlowContainerFragment<HomeFlowNavHostFragment>(
    HomeFlowNavHostFragment::class.java,
    R.layout.navigation_fragment_home
)

class HomeFlowNavHostFragment: FlowNavHostFragment(true,  true)