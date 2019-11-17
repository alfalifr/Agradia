package id.go.surabaya.disperdagin.utilities

import java.text.SimpleDateFormat
import java.util.*

class DateUtil{
    companion object{
        fun timestamp(): String{
            val simpleDateFormat = SimpleDateFormat("yyyyddMMhhmmss")
            return simpleDateFormat.format(Date())
        }
    }
}