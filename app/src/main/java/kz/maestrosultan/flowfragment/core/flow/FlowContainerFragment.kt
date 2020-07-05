package kz.maestrosultan.flowfragment.core.flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

open class FlowContainerFragment(
    @LayoutRes private val navigationResId: Int
): Fragment() {

    protected lateinit var navHost: FlowNavHostFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(navigationResId, container, false)
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)

        if (childFragment is FlowNavHostFragment) {
            navHost = childFragment
        }
    }
}