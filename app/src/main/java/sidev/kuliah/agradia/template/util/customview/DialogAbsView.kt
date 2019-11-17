package id.go.surabaya.disperdagin.utilities.customview

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import id.go.surabaya.disperdagin.R
import kotlinx.android.synthetic.main.dialog_container_cardview.view.*

//import kotlinx.android.synthetic.main.dialog_list.view.*

abstract class DialogAbsView(val c: Context){
    /**
     * Jangan langsung memodifikasi var ini
     */
    var dialog: AlertDialog
    protected set
//    lateinit var adp: DialogListAdapter
//    lateinit var rv: RecyclerView
    protected val layoutContainerId= R.layout.dialog_container_cardview
    protected val layoutContainerView: View
    protected abstract val layoutId: Int
    protected val layoutView: View

//    private var listener: DialogListAdapter.DialogListListener?= null

    init{
        layoutView= LayoutInflater.from(c).inflate(layoutId, null, false)
        layoutContainerView= LayoutInflater.from(c).inflate(layoutContainerId, null, false) //as ViewGroup
        layoutContainerView.findViewById<LinearLayout>(R.id.vg_content_container)
            .addView(layoutView)
        dialog= AlertDialog.Builder(c)
            .setView(layoutContainerView)
            .setCancelable(true)
            .create()
        dialog.window
            .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun <V: View> findViewInContainer(id: Int): V{
        return layoutContainerView.findViewById(id)
    }
    fun <V: View> findView(id: Int): V{
        return layoutView.findViewById(id)
    }

    fun setTitle(title: String){
        layoutContainerView.tv_title.text= title
    }

    fun setTitleVisible(visible: Boolean){
        val vis= if(visible) View.VISIBLE
            else View.GONE
        layoutContainerView.tv_title.visibility= vis
    }


    fun show(){
        dialog.show()
    }

    fun cancel(){
        dialog.cancel()
    }
}