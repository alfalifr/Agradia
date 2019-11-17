package id.go.surabaya.disperdagin.utilities.chartopr

import id.go.surabaya.disperdagin.models.MonitoringChartModel
import id.go.surabaya.disperdagin.models.MonitoringChartModelRaw
import java.util.*
import kotlin.collections.ArrayList

class DataTransformer {
    companion object{
        fun getDate(data: MonitoringChartModelRaw): String{
            return data.date!!.substring(0, 2)
        }
        fun getMonth(data: MonitoringChartModelRaw): String{
            return data.date!!.substring(3, 5)
        }
        fun getYear(data: MonitoringChartModelRaw): String{
            return data.date!!.substring(4)
        }

        /**
         * Mengubah bentuk
         * dari MonitoringChartModelRaw menjadi MonitoringChartModel
         * dg melengkapi data yang rumpang (lebih panjang dari mMPAndroidChart_clean)
         */
        fun mMPAndroidChart_transform(data: ArrayList<MonitoringChartModelRaw>)
                : ArrayList<MonitoringChartModel>
        {
            val dataFit= ArrayList<MonitoringChartModel>()
            if(data.isNotEmpty()){
                //1.====Menghitung jumlah hari pada interval tertentu======
                //1.0.Init
                val cal= Calendar.getInstance()
                val year= cal.get(Calendar.YEAR)
                val month= cal.get(Calendar.MONTH) //mulai 0
                val date= cal.get(Calendar.DAY_OF_MONTH)

                //1.1.Menghitung banyaknya bulan
                var monthInItr= getMonth(data[0]) //.date!!.substring(3, 5)
                var months= ArrayList<Int>()
                months.add(monthInItr.toInt())
                for(perData in data){
                    val monthInData= getMonth(perData) //.date!!.substring(3, 5)
                    if(monthInItr != monthInData){
                        monthInItr= monthInData
                        months.add(monthInItr.toInt())
                    }
                }

                //1.2.Menghitung banyaknya hari
                var dayCount= 0
                val monthsBorder= months.size-1 //agar dayCount tehir ditambah dg tgl yg akhir
                val datesMaxInMonth= Array(months.size){i ->
                    var dateInData= cal.get(Calendar.DAY_OF_MONTH)
                    if(i < monthsBorder){
                        cal.set(year, months[i], date)
                        dayCount += dateInData
                    }
                    dateInData
                }
                cal.set(year, months.last(), date)
                dayCount += cal.get(Calendar.DAY_OF_MONTH)

                //1.3. Mengurangi dayCount dg tgl yg paling awal
                val dateFirst= getDate(data.first()).toInt() //.date!!.substring(0, 2).toInt()
                dayCount -= dateFirst+1

                //2.====Memproses tiap data menjadi data yg fit dg chart======
                var dateInItr= dateFirst
                var monthInd= 0
                var dataInd= 0
                for(i in 0 until dayCount){
                    if(dateInItr > datesMaxInMonth[monthInd]){
                        monthInd++
                        dateInItr= 1
                    }
                    var y= -1
                    if(getDate(data[dataInd]).toInt() == dateInItr){
                        y= data[dataInd].data
                        dataInd++
                    }
                    dataFit.add(MonitoringChartModel(i, y))
                    dateInItr++
                }
            }
            return dataFit
        }

        /**
         * Membersihkan data yang bernilai -1
         * sekaligus mengganti period dari data yg masuk
         */
        fun mMPAndroidChart_clean(data: ArrayList<MonitoringChartModel>)
                : ArrayList<MonitoringChartModel>
        {
            val dataClean= ArrayList<MonitoringChartModel>()
            var dataCleanInd= 0
            for(perData in data){
                if(perData.data >= 0){
                    dataClean.add(perData.copy())
                    perData.period= dataCleanInd++
                }
            }
            return dataClean
        }
/*
        class transformToFit{
            companion object{
            }
        }
 */
    }
}