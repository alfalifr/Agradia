package id.go.surabaya.disperdagin.utilities.customview

import android.content.Context
import android.graphics.PorterDuff
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Utils
import kotlinx.android.synthetic.main.chart_marker_view.view.*

class ChartMarkerView(val c: Context, val resId: Int) : MarkerView(c, resId) {

//    internal var tvContent: TextView
//    internal var tvTitle: TextView

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        if (e is CandleEntry) {
            val ce = e as CandleEntry?
            tv_content.text = Utils.formatNumber(ce!!.high, 0, true)
        } else {
            val dataset=
                chartView.data.getDataSetByIndex(highlight!!.dataSetIndex)//.getLabel()
            val label= dataset.label
            val color= dataset.color

            tv_label.text= label
//            tv_title.setText(label )
            rl_content_container.background.setColorFilter(color, PorterDuff.Mode.ADD)
            iv_corner.background.setColorFilter(color, PorterDuff.Mode.ADD)

            val period= e!!.x
            val data= e.y
            tv_content.text = "$period, $data"//Utils.formatNumber(e!!.y, 0, true)
        }
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
    }
}
