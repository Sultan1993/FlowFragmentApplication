package kz.maestrosultan.flowfragment.core.flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

@Suppress("UNCHECKED_CAST")
open class FlowContainerFragment<NavHost: FlowNavHostFragment>(
    private val kClass: Class<NavHost>,
    @LayoutRes private val navigationResId: Int
): Fragment() {

    protected lateinit var navHost: NavHost

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(navigationResId, container, false)
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)

        if (kClass.isAssignableFrom(childFragment::class.java)) {
            navHost = childFragment as NavHost
        }
    }
}