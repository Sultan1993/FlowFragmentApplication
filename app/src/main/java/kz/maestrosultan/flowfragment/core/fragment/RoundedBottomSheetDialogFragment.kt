package kz.maestrosultan.flowfragment.core.fragment

import android.content.DialogInterface
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kz.maestrosultan.flowfragment.R

interface DialogDismissListener {
    fun onDialogDismissed()
}

open class RoundedBottomSheetDialogFragment(
    private val style: Int = R.style.BottomSheetTheme
) : BottomSheetDialogFragment() {

    var dialogDismissListener: DialogDismissListener? = null

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