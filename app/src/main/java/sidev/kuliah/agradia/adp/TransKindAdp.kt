package sidev.kuliah.agradia.adp

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.comp_item_oval_text.view.*
import sidev.kuliah.agradia.R
import sidev.kuliah.agradia.template.adp.linear.LinearRVAdp

class TransKindAdp(c: Context, dataList: ArrayList<String>?)
    : LinearRVAdp<String>(c, dataList){
    override val layoutId: Int
        get() = R.layout.comp_item_oval_text
    override var orientation: Int= LinearLayoutManager.HORIZONTAL

    override fun bindVH(vh: SimpleViewHolder, pos: Int, data: String) {
        val v= vh.itemView
        v.tv.text= data
        v.setOnClickListener { v ->
            listener?.transKindClick(v!!, pos)
            if(selectedItemPos >= 0)
                listener?.transKindUnClick(selectedItemView, selectedItemPos)
            selectItem(v, pos)
        }
    }

    var listener: TransKindListener?= null

    interface TransKindListener{
        fun transKindClick(v: View, pos: Int)
        fun transKindUnClick(v: View?, pos: Int)
    }
}