package id.go.surabaya.disperdagin.utilities

import com.github.mikephil.charting.utils.ColorTemplate

class ColorChartUtil{
    companion object{
        fun getDefaultColor(): ArrayList<Int>{
            val colors= ArrayList<Int>()
            for (c in ColorTemplate.VORDIPLOM_COLORS)
                colors.add(c)

            for (c in ColorTemplate.JOYFUL_COLORS)
                colors.add(c)

            for (c in ColorTemplate.COLORFUL_COLORS)
                colors.add(c)

            for (c in ColorTemplate.LIBERTY_COLORS)
                colors.add(c)

            for (c in ColorTemplate.PASTEL_COLORS)
                colors.add(c)

            return colors
        }
    }
}