package kz.maestrosultan.flowfragment.core.flow

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import kz.maestrosultan.flowfragment.core.fragment.RoundedBottomSheetFullScreenDialogFragment

@Suppress("UNCHECKED_CAST")
open class BottomSheetFlowContainerFragment(
    @LayoutRes private val navigationResId: Int,
    private val cancelable: Boolean = false
): RoundedBottomSheetFullScreenDialogFragment(cancellable = cancelable) {

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

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        navHost.navStackHandler?.popNavigationController()
    }
}