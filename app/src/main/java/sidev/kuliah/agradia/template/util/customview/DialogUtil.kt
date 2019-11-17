package id.go.surabaya.disperdagin.utilities.customview

import android.app.DatePickerDialog
import android.content.Context
import java.util.*
//import jdk.nashorn.internal.objects.NativeDate.getTime
import java.text.SimpleDateFormat


class DialogUtil {
    companion object{
        val dateFormat= "dd/MM/yyyy"
        fun showDatePicker(c: Context, minDate: Date?= null, style: Int?= null,
                           func: (dateStr: String) -> Unit): DatePickerDialog{
            val cal= Calendar.getInstance()

            val listener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // TODO Auto-generated method stub
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                //val myFormat = //In which you need put here
                val sdf = SimpleDateFormat(dateFormat, Locale.getDefault())

                func(sdf.format(cal.time))
            }

            val picker= DatePickerDialog(
                    c, style ?: 0, listener,
                    cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
            )
            if(minDate != null)
                picker.datePicker.minDate= minDate.time
            picker.show()
            return picker
        }
        fun parseToDate(str: String): Date{
            return SimpleDateFormat(dateFormat, Locale.getDefault()).parse(str)
        }
    }
}