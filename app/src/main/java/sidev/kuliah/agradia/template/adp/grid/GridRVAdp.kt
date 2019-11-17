package sidev.kuliah.agradia.template.adp.grid

import android.content.Context
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.comp_item_grid_pict_text_over_sm.view.*

abstract class GridRVAdp<D>(c: Context, dataList: ArrayList<D>?)
    : GridSquareRVAdp<D>(c, dataList){

    open var columnHeight= 20

    override fun bindVH_internal(vh: SimpleViewHolder) {
        val v= vh.itemView
        val lpCard= LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        )
        lpCard.setMargins(margin, margin, margin, margin)
        v.cv_main_container.layoutParams= lpCard

        v.layoutParams.width= columnWidht
        v.layoutParams.height= columnHeight
    }
}