package kz.maestrosultan.flowfragment.core.fragment

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kz.maestrosultan.flowfragment.R

open class RoundedBottomSheetFullScreenDialogFragment(
    private val style: Int = R.style.BottomSheetTheme,
    private val cancellable: Boolean = false
) : RoundedBottomSheetDialogFragment(style) {

    override fun onCreateDialog(savedInstanceState: Bundle?): BottomSheetDialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupFullHeight(bottomSheetDialog)
        }

        return dialog
    }

    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet).apply {
            skipCollapsed = true
            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(p0: View, p1: Float) { }

                override fun onStateChanged(view: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING && !cancellable) {
                        state = BottomSheetBehavior.STATE_EXPANDED
                    }

                    if (newState == BottomSheetBehavior.STATE_HIDDEN && cancellable) {
                        dialog?.dismiss()
                    }
                }
            })
        }

        val layoutParams = bottomSheet.layoutParams
        val windowHeight = Resources.getSystem().displayMetrics.heightPixels -  getStatusBarHeight()
        if (layoutParams != null) {
            layoutParams.height = windowHeight
        }
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getStatusBarHeight(): Int {
        val rect = Rect()
        val window = activity?.window
        window?.decorView?.getWindowVisibleDisplayFrame(rect)
        return rect.top
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.attributes?.windowAnimations =
            R.style.BottomSheetAnimation
    }
}