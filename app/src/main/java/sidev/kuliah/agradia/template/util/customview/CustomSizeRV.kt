package id.go.surabaya.disperdagin.utilities.customview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import id.go.surabaya.disperdagin.utilities.ViewUtil

class CustomSizeRV : RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        val newHeightSpec = MeasureSpec.makeMeasureSpec(ViewUtil.dpToPx(300), MeasureSpec.AT_MOST)
        super.onMeasure(widthSpec, newHeightSpec)
    }
}