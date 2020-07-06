package kz.maestrosultan.flowfragment.notifications

import kz.maestrosultan.flowfragment.R
import kz.maestrosultan.flowfragment.core.flow.FlowContainerFragment
import kz.maestrosultan.flowfragment.core.flow.FlowNavHostFragment

class NotificationsFlowFragment: FlowContainerFragment<NotificationsFlowNavHostFragment>(
    NotificationsFlowNavHostFragment::class.java,
    R.layout.navigation_fragment_notifications
)

class NotificationsFlowNavHostFragment: FlowNavHostFragment(true)