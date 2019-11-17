package sidev.kuliah.agradia.template.adp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class SimpleRecyclerViewAdapter <D> (
    val ctx: Context, private var dataList: ArrayList<D>?
    )
    : RecyclerView.Adapter<SimpleRecyclerViewAdapter<D>.SimpleViewHolder>(){

    var rv: RecyclerView?= null
        set(v){
            field?.adapter= null
            field= v
            updateRv()
        }
    var selectedItemPos= -1
    var selectedItemView: View?= null

    abstract val layoutId: Int

    abstract fun bindVH(vh: SimpleViewHolder, pos: Int, data: D)
    abstract fun bindVH_internal(vh: SimpleViewHolder)

    open inner class SimpleViewHolder(v: View): RecyclerView.ViewHolder(v)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val v= LayoutInflater.from(ctx).inflate(layoutId, parent, false)
        return SimpleViewHolder(v)
    }

    override fun getItemCount(): Int {
        return when(dataList){
            null -> 0
            else -> dataList!!.size
        }
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        bindVH_internal(holder)
        bindVH(holder, position, dataList!![position])
    }

    protected open fun updateRv(){
        rv!!.adapter= this
    }

    fun selectItem(v: View?, pos: Int){
        selectedItemView= v
        selectedItemPos= pos
    }

    fun updateData(dataList: ArrayList<D>?){
        this.dataList= dataList
        notifyDataSetChanged()
    }
}
