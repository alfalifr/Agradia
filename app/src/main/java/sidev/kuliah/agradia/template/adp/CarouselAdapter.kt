package sidev.kuliah.agradia.template.adp

import sidev.kuliah.agradia.template._init.R_color
import sidev.kuliah.agradia.template._init.R_drawable
import sidev.kuliah.agradia.template.util.UkuranUtil

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout

import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

import java.util.ArrayList


abstract class CarouselAdapter<M>(val ctx: Context, var dataList: ArrayList<M>?)
    : PagerAdapter() {

    enum class Color{
        ACTIVE, INACTIVE
    }

    var vp: ViewPager?= null
        set(v){
            field?.adapter= null
            field= v
            updateVp()
        }
    var dotContainer: LinearLayout?= null
        set(v){
            field= v
            fillDot()
        }
    private var themeColor= HashMap<Color, Int>()
    private var activeDotInd = -1
    private var adpInit= false
    var listener: CarouselAdapterListener<M>?= null


    interface CarouselAdapterListener <M>{
        fun onCarouselItemClick(v: View, data: M)
    }

    init {
        themeColor[Color.ACTIVE]= ContextCompat.getColor(ctx, R_color.colorPrimaryDark)
        themeColor[Color.INACTIVE]= ContextCompat.getColor(ctx, R_color.colorAccent)
    }


    abstract fun createView(container: ViewGroup, pos: Int, data: M): View

    override fun instantiateItem(container: ViewGroup, pos: Int): Any {
        val posNew= posNew(pos)
        val data= dataList!![posNew]
        val v= createView(container, posNew, data)
        v.setOnClickListener{v ->
            listener?.onCarouselItemClick(v!!, data)
        }
        container.addView(v)
        return v
    }

    override fun getCount(): Int {
        return when(dataList){
            null -> 0
            else -> dataList!!.size +2
        }
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    private fun updateVp() {
        vp!!.adapter= this
        vp!!.addOnPageChangeListener(
            object: ViewPager.OnPageChangeListener{
                override fun onPageScrollStateChanged(state: Int) {}

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {}
                override fun onPageSelected(position: Int) {
                    activeDot(position)
                }
            }
        )
    }


    private fun fillDot() {
        if (dotContainer != null) {
            dotContainer!!.removeAllViews()
            /*Log.e("TES_HMM", "getCount()= " +getCount());
            Log.e("TES_HMM", "getCount()= " +getCount());
            Log.e("TES_HMM", "getCount()= " +getCount());*/
            for (i in 0 until count - 2) {
                val iv = ImageView(ctx)
                iv.setImageResource(R_drawable.shape_dot)
                val size = UkuranUtil.DpKePx(20, ctx)
                val margin = UkuranUtil.DpKePx(4, ctx)
                val lp = LinearLayout.LayoutParams(size, size)
                lp.setMargins(margin, margin, margin, margin)
                lp.rightMargin = 200
                iv.layoutParams = lp
                iv.setPadding(margin, margin, margin, margin)
                iv.layoutParams = ViewGroup.LayoutParams(40, 40)
                iv.setColorFilter(ContextCompat.getColor(ctx, R_color.white))
                dotContainer!!.addView(iv)
            }
        }
    }

    fun activeDot(ind: Int) {
        if(dotContainer != null){
            val colorActive = themeColor[Color.ACTIVE]!!
            val colorNon = themeColor[Color.INACTIVE]!!
            if (activeDotInd >= 0)
                (dotContainer!!.getChildAt(activeDotInd) as ImageView).setColorFilter(colorNon) //.getDrawable().setTint(colorNon);
            activeDotInd= ind
            (dotContainer!!.getChildAt(activeDotInd) as ImageView).setColorFilter(colorActive) //.getDrawable().setTint(colorActive);
        }
    }

    fun posNew(pos: Int): Int {
        var posBaru = pos

        if (posBaru == count - 1)
            posBaru = 1
        else if (posBaru == 0)
            posBaru = count - 2

        posBaru -= 1
        return posBaru
    }
}