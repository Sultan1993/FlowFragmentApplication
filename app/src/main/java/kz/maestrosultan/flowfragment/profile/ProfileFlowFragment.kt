package kz.maestrosultan.flowfragment.profile

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.navArgs
import kotlinx.android.parcel.Parcelize
import kz.maestrosultan.flowfragment.R
import kz.maestrosultan.flowfragment.core.flow.FlowContainerFragment
import kz.maestrosultan.flowfragment.core.flow.FlowNavHostFragment

@Parcelize
data class ProfileFlowArgs(
    val toDetails: Boolean
): Parcelable

class ProfileFlowFragment: FlowContainerFragment<ProfileFlowNavHostFragment>(
    ProfileFlowNavHostFragment::class.java,
    R.layout.navigation_fragment_profile
) {

    private val arguments: ProfileFlowFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments.flowArgs.let {
            if (it.toDetails) {
                startFromDetails()
            }
        }
    }

    private fun startFromDetails() {
        val graph = navHost.navController.graph
        graph.startDestination = R.id.profileDetailsFragment
        navHost.navController.setGraph(graph, bundleOf())
    }
}

class ProfileFlowNavHostFragment: FlowNavHostFragment()