package sidev.kuliah.agradia.adp

import android.content.Context
import kotlinx.android.synthetic.main.comp_item_grid_square_text_3.view.*
import sidev.kuliah.agradia.R
import sidev.kuliah.agradia.model.ProdModel
import sidev.kuliah.agradia.template.adp.grid.GridRVAdp

class ProdExploreAdp(c: Context, dataList: ArrayList<ProdModel>?)
    : GridRVAdp<ProdModel>(c, dataList){
    override val layoutId: Int
        get() = R.layout.comp_item_grid_square_text_3

    override fun bindVH(vh: SimpleViewHolder, pos: Int, data: ProdModel) {
        val v= vh.itemView
        v.tv_title.text= data.name
        v.tv_desc_1.text= data.seller
        v.tv_desc_2.text= "${data.price} / ${data.unit}"
        v.iv_pict.setImageResource(data.pict)
    }
}