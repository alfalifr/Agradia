package id.go.surabaya.disperdagin.utilities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.pm.PackageManager
import android.os.Build
import android.util.Base64
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.content.pm.ApplicationInfo



object Util{
    const val MAIN_REF = "main_ref" // ini merupakan tag untuk menyimpan di share preference
    const val KEY_PREF_USER_ID = "key_pref_user_id"
    const val KEY_PREF_USER_TOKEN = "key_pref_user_token"
    const val KEY_PREF_USER_EMAIL = "key_pref_user_email"
    const val KEY_PREF_USER_TYPE = "key_pref_user_type"

    fun setSharedPref(c: Context, value: String?, key: String) {
        val editor = c.getSharedPreferences(MAIN_REF, MODE_PRIVATE).edit()
        editor.putString(key, value)
        editor.commit()
    }

    //untuk mendapatkan status negara pengguna
    fun getSharedPref(c: Context, key: String): String? {
        val prefs = c.getSharedPreferences(MAIN_REF, MODE_PRIVATE)
        return prefs.getString(key, null)
    }

    //untuk mengubah dari double menjadi formatted nomimal dalam bentuk string
    fun convertToFormattedValue(value : Long) : String {
        val valTemp = value.toString()
        var finalVal = ""

        for(i in valTemp.length - 1 downTo 0){
            finalVal = if((i - valTemp.length) % 3 == 0 && i != 0)
                "." + valTemp[i] + finalVal
            else
                valTemp[i] + finalVal
        }

        return "$finalVal,-"
    }

    // untuk search string
    fun searchString(toBeSearch: String, keyword: String) : Boolean {

        for(i in 0 .. toBeSearch.length - keyword.length)
            if(toBeSearch.substring(i, i + keyword.length).equals(keyword, ignoreCase = true))
                return true

        return false
    }

    // agar key maupun url dari server biar apan maka setidaknya di encode
    fun encodeBase64(str: String): String {
        val encodedBytes = Base64.encode(str.toByteArray(), Base64.DEFAULT)
        return String(encodedBytes)
    }

    fun decodeBase64(str: String): String {
        val decodedBytes = Base64.decode(str.toByteArray(), Base64.DEFAULT)
        return String(decodedBytes)
    }

    fun isStoragePermissionGranted(context: Activity): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            return if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                // Log.v(TAG,"Permission is granted");
                true
            } else {

                //Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(
                    context,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
                false
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            //Log.v(TAG,"Permission is granted");
            return true
        }
    }

    fun convertStringToDouble(str: String?): Double{
        if(str == null)
            return -1.0
        if(str.equals(""))
            return -1.0
        return str.toDouble()
    }

    fun isFineLocationPermissionGranted(context: Activity): Boolean {
        return if(Build.VERSION.SDK_INT >= 23) {
            if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                true
            else {
                ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
                false
            }
        } else
            true
    }

    fun isGoogleMapsInstalled(ctx: Activity): Boolean {
        return try {
            val info = ctx.getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }

    }
}