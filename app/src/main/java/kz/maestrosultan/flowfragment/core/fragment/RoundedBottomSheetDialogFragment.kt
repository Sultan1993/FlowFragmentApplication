package kz.maestrosultan.flowfragment.core.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kz.maestrosultan.flowfragment.R

interface DialogDismissListener {
    fun onDialogDismissed()
}

open class RoundedBottomSheetDialogFragment(
    private val style: Int = R.style.BottomSheetTheme
) : BottomSheetDialogFragment() {

    protected var dialogDismissListener: DialogDismissListener? = null

    protected val behavior by lazy {
        val bottomSheet = dialog?.findViewById(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
        BottomSheetBehavior.from(bottomSheet)
    }

    override fun getTheme(): Int = style

    override fun onCreateDialog(savedInstanceState: Bundle?) = BottomSheetDialog(requireContext(), theme)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.BottomSheetAnimation
    }
    
    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dialogDismissListener?.onDialogDismissed()
    }
}