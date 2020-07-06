package kz.maestrosultan.flowfragment.dashboard

import kz.maestrosultan.flowfragment.R
import kz.maestrosultan.flowfragment.core.flow.FlowContainerFragment
import kz.maestrosultan.flowfragment.core.flow.FlowNavHostFragment

class DashboardFlowFragment: FlowContainerFragment<DashboardFlowNavHostFragment>(
    DashboardFlowNavHostFragment::class.java,
    R.layout.navigation_fragment_dashboard
)

class DashboardFlowNavHostFragment: FlowNavHostFragment(true,  true)