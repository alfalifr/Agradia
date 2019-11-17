package sidev.kuliah.agradia.frag.home

import android.view.View
import kotlinx.android.synthetic.main.frag_home_belanja.*
import sidev.kuliah.agradia.R
import sidev.kuliah.agradia.adp.CarouselHomeAdapter
import sidev.kuliah.agradia.adp.ProdCategAdp
import sidev.kuliah.agradia.adp.ProdExploreAdp
import sidev.kuliah.agradia.template.frag.SimpleAbsFrag

class BelanjaHomeFrag : SimpleAbsFrag(){
    override val layoutId: Int
        get() = R.layout.frag_home_belanja

    lateinit var carouselAdp: CarouselHomeAdapter
    lateinit var prodCategAdp: ProdCategAdp
    lateinit var prodExploreAdp: ProdExploreAdp

    override fun initView(layoutView: View) {
        carouselAdp= CarouselHomeAdapter(context!!, null)
        carouselAdp.vp= vp_carousel

        prodCategAdp= ProdCategAdp(context!!, null)
        prodCategAdp.rv= rv_category

        prodExploreAdp= ProdExploreAdp(context!!, null)
        prodExploreAdp.rv= rv_explore
    }
}