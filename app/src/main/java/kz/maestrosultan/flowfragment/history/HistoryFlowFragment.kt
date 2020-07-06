package kz.maestrosultan.flowfragment.history

import kz.maestrosultan.flowfragment.R
import kz.maestrosultan.flowfragment.core.flow.FlowContainerFragment
import kz.maestrosultan.flowfragment.core.flow.FlowNavHostFragment

class HistoryFlowFragment: FlowContainerFragment<HistoryFlowNavHostFragment>(
    HistoryFlowNavHostFragment::class.java,
    R.layout.navigation_fragment_history
)

class HistoryFlowNavHostFragment: FlowNavHostFragment(true)