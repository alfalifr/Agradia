package id.go.surabaya.disperdagin.utilities.customview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import id.go.surabaya.disperdagin.R
import id.go.surabaya.disperdagin.utilities.ViewUtil

class DialogWarningView(c: Context): DialogAbsView(c){
    override val layoutId: Int
        get() = R.layout.dialog_warning

    init {
        setTitleVisible(false)
    }


    fun setContent(content: String){
        val tv= findView<TextView>(R.id.tv_title)
        tv.text= content
    }

    fun setSolution(solution: String){
        val tv= findView<TextView>(R.id.tv_solution)
        if(solution.isBlank())
            tv.visibility= View.GONE
        else{
            tv.visibility= View.VISIBLE
            tv.text= solution
        }
    }

    fun setSolutionIcon(icon: Int){
        findView<ImageView>(R.id.iv_solution).setImageResource(icon)
    }
    fun setSolutionIconColor(color: Int){
        findView<ImageView>(R.id.iv_solution).setColorFilter(color)
    }

    fun setSolutionVisible(visible: Boolean){
        val vis= if(visible) View.VISIBLE
            else View.GONE
        findView<View>(R.id.rl_solution_container).visibility= vis
    }

    fun setSolutionOnClickListener(func: (v: View) -> Unit){
        findView<ImageView>(R.id.iv_solution).setOnClickListener(func)
    }
}