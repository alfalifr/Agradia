package sidev.kuliah.agradia.adp

import android.content.Context
import kotlinx.android.synthetic.main.comp_item_grid_pict_text_over_sm.view.*
import sidev.kuliah.agradia.R
import sidev.kuliah.agradia.model.PictTextModel
import sidev.kuliah.agradia.template.adp.grid.GridSquareRVAdp

class ProdCategAdp(c: Context, dataList: ArrayList<PictTextModel>?)
    : GridSquareRVAdp<PictTextModel>(c, dataList){
    override val layoutId: Int
        get() = R.layout.comp_item_grid_pict_text_over_sm

    override fun bindVH(vh: SimpleViewHolder, pos: Int, data: PictTextModel) {
        val v= vh.itemView
        v.tv.text= data.text
        v.iv.setImageResource(data.pict)
    }
}