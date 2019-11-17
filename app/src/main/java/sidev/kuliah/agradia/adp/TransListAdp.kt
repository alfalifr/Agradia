package sidev.kuliah.agradia.adp

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import sidev.kuliah.agradia.template.adp.linear.LinearRVAdp

class TransListAdp (c: Context, dataList: ArrayList<String>?)
    : LinearRVAdp<String>(c, dataList){
    override val layoutId: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override var orientation: Int= LinearLayoutManager.VERTICAL

    override fun bindVH(vh: SimpleViewHolder, pos: Int, data: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}