package kz.maestrosultan.flowfragment.auth

import kz.maestrosultan.flowfragment.R
import kz.maestrosultan.flowfragment.core.flow.BottomSheetFlowContainerFragment
import kz.maestrosultan.flowfragment.core.flow.FlowNavHostFragment

class AuthFlowFragment: BottomSheetFlowContainerFragment(
    R.layout.navigation_fragment_auth,
    true
)

class AuthFlowNavHostFragment: FlowNavHostFragment()