package id.go.surabaya.disperdagin.utilities.act

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity



class IntentUtil{
    companion object{
/*
        val countryCode= HashMap<String, String>()
        init {
            countryCode["ID"]= "62"
        }
 */

        fun openWa(act: Activity, number: String){
            val numberUsed=
                if(number.startsWith("0")) "62${number.substring(1)}"
                else number
            val url = "https://api.whatsapp.com/send?phone=$numberUsed"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            act.startActivity(i)
        }
        // open map with coordinates
        fun openMap(act: Activity, lat: String, long: String, locationName: String){
            val strUri = "http://maps.google.com/maps?q=loc:$lat,$long ($locationName)"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
            intent.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"
            )
            act.startActivity(intent)
         /*
            val gmmIntentUri = Uri.parse("geo:$lat,$long")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            act.startActivity(mapIntent)*/
        }
    }
}