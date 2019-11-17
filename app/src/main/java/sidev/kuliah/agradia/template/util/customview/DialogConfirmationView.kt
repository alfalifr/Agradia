package id.go.surabaya.disperdagin.utilities.customview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import id.go.surabaya.disperdagin.R
import id.go.surabaya.disperdagin.utilities.ViewUtil

class DialogConfirmationView(c: Context): DialogAbsView(c){

    enum class ButtonKind{
        RIGHT, LEFT
    }

    override val layoutId: Int
        get() = R.layout.dialog_confirm


    fun makeBtnHollow(kind: ButtonKind){
        val btn= getBtn(kind)
        btn.setBackgroundResource(R.drawable.shape_border_square_round_edge_main)
        btn.setTextColor(ContextCompat.getColor(c, R.color.colorPrimaryDark))
    }

    fun makeBtnSolid(kind: ButtonKind){
        val btn= getBtn(kind)
        btn.setBackgroundResource(R.drawable.shape_solid_square_round_edge_fill)
        btn.setTextColor(ContextCompat.getColor(c, R.color.colorPrimaryDark))
    }

    fun setOnClickListener(kind: ButtonKind, func: (v: View) -> Unit){
        val btn= getBtn(kind)
        btn.setOnClickListener(func)
    }

    fun getBtn(kind: ButtonKind): Button{
        return when(kind){
            ButtonKind.RIGHT -> findView(R.id.btn_right)
            ButtonKind.LEFT -> findView(R.id.btn_left)
        }
    }
}