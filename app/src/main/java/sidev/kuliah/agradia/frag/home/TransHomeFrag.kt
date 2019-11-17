package sidev.kuliah.agradia.frag.home

import android.graphics.PorterDuff
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.comp_item_oval_text.view.*
import kotlinx.android.synthetic.main.frag_home_trans.*
import sidev.kuliah.agradia.R
import sidev.kuliah.agradia.adp.TransKindAdp
import sidev.kuliah.agradia.adp.TransListAdp
import sidev.kuliah.agradia.template.frag.SimpleAbsFrag

class TransHomeFrag : SimpleAbsFrag(), TransKindAdp.TransKindListener{
    override val layoutId: Int
        get() = R.layout.frag_home_trans

    lateinit var transKindAdp: TransKindAdp
    lateinit var transListAdp: TransListAdp

    override fun initView(layoutView: View) {
        val dataTransKindRaw= arrayOf(
            "Diproses", "Dikirim", "Sampai", "Selesai"
        )
        val dataTransKind= ArrayList<String>(dataTransKindRaw.toMutableList())

        transKindAdp= TransKindAdp(context!!, dataTransKind)
        transKindAdp.rv= rv_trans_kind
        transKindAdp.listener= this

        transListAdp= TransListAdp(context!!, null)
        transListAdp.rv= rv_trans
    }

    override fun transKindClick(v: View, pos: Int) {
        v.tv.background.setColorFilter(ContextCompat.getColor(context!!, R.color.colorPrimaryDark), PorterDuff.Mode.DST_ATOP)
        v.tv.setTextColor(ContextCompat.getColor(context!!, R.color.colorPrimaryDark))
    }

    override fun transKindUnClick(v: View?, pos: Int) {
        if(v != null){
            v.tv.background.setColorFilter(ContextCompat.getColor(context!!, R.color.black), PorterDuff.Mode.DST_ATOP)
            v.tv.setTextColor(ContextCompat.getColor(context!!, R.color.black))
        }
    }
}