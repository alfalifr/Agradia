package sidev.kuliah.agradia.adp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import sidev.kuliah.agradia.model.PictTextModel
import sidev.kuliah.agradia.template._init.R_id
import sidev.kuliah.agradia.template._init.R_layout
import sidev.kuliah.agradia.template.adp.CarouselAdapter

class CarouselHomeAdapter(c: Context, data: ArrayList<PictTextModel>?)
    : CarouselAdapter<PictTextModel>(c, data){

    override fun createView(container: ViewGroup, pos: Int, data: PictTextModel): View {
        val inflater = LayoutInflater.from(ctx)
        val v = inflater.inflate(R_layout.comp_item_list_carousel_home, container, false)

        val tv = v.findViewById<TextView>(R_id.tv)
        val iv = v.findViewById<ImageView>(R_id.iv)

        tv.text= data.text
        iv.setImageResource(data.pict)
        return v
    }
}