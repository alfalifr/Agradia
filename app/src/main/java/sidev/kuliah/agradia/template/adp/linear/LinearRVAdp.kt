package sidev.kuliah.agradia.template.adp.linear

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import sidev.kuliah.agradia.template.adp.SimpleRecyclerViewAdapter

abstract class LinearRVAdp <D>(c: Context, dataList: ArrayList<D>?)
    : SimpleRecyclerViewAdapter<D>(c, dataList){

    open var orientation= LinearLayoutManager.VERTICAL
        set(v){
            field= v
            updateRv()
        }

    override fun bindVH_internal(vh: SimpleViewHolder) {}

    override fun updateRv() {
        super.updateRv()
        val l= LinearLayoutManager(ctx)
        l.orientation= orientation
    }
}