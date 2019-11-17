package sidev.kuliah.agradia.template.adp.grid

import android.content.Context
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.go.surabaya.disperdagin.utilities.ViewUtil
import kotlinx.android.synthetic.main.comp_item_grid_pict_text_over_sm.view.*
import sidev.kuliah.agradia.R
import sidev.kuliah.agradia.model.PictTextModel
import sidev.kuliah.agradia.template.adp.SimpleRecyclerViewAdapter

abstract class GridSquareRVAdp<D>(c: Context, dataList: ArrayList<D>?)
    : SimpleRecyclerViewAdapter<D>(c, dataList){
    override val layoutId: Int
        get() = R.layout.comp_item_grid_pict_text_over_sm

    //biar gak infinite loop pass manggil set(v)
    private var modInside_columnCount= false
    private var modInside_columnWidht= false
//    private var modInside_margin= false
    var columnCount= 1
        set(v){
            modInside_columnCount= true
            field= v
            if(!modInside_columnWidht)
                columnWidht= ViewUtil.calculateColumnWidth(ctx, v)
            updateRv()
            modInside_columnCount= false
        }
    /**
     * Untuk layout terluar
     * Dalam bentuk pixel
     */
    var columnWidht= 1
        set(v){
            modInside_columnWidht= true
            field= v
            if(!modInside_columnCount)
                columnCount= ViewUtil.calculateColumnNumber(ctx, v)
            notifyDataSetChanged()
            modInside_columnWidht= false
        }

    /**
     * Margin untuk child pertama (CardView)
     */
    var margin= 10
        set(v){
            field= v
            notifyDataSetChanged()
        }

    override fun updateRv(){
        super.updateRv()
        rv!!.layoutManager= GridLayoutManager(ctx, columnCount)
    }

    override fun bindVH_internal(vh: SimpleViewHolder) {
        val v= vh.itemView
        val lpCard= LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        )
        lpCard.setMargins(margin, margin, margin, margin)
        v.cv_main_container.layoutParams= lpCard

        v.layoutParams.width= columnWidht
        v.layoutParams.height= columnWidht
    }
}