package id.go.surabaya.disperdagin.utilities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import com.loopj.android.http.RequestParams
import com.squareup.picasso.Picasso
import id.go.surabaya.disperdagin.interfaces.CallBack
import id.go.surabaya.disperdagin.utilities.dataoperation.AuthOps

class ServerUtil(private val context: Context) {
    //------------ BLA BLA ---------------
    // ini untuk tipe ukuran gambar yang akan ditampilkan di aplikasi android

    companion object {
        val IMAGE_SIZE_TUMBNAIL_SMALL = 0
        val IMAGE_SIZE_TUMBNAIL_MEDIUM = 1
        val IMAGE_SIZE_DETAILED = 2
    }

    private val currentDate: String
        get() {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            return dateFormat.format(calendar.time)
        }

    fun post(requestParams: RequestParams, endPoint: String, callBack : CallBack) {
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivity.activeNetworkInfo

        if(activeNetwork != null){
            val connection = AsyncHttpClient()

            // untuk memasukan parameter dari post request
            connection.post(endPoint, requestParams, object : AsyncHttpResponseHandler(){
                override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {

                    if(responseBody == null){
                        callBack.stringCallBack(Constants.NULL_RESPONSE)
                        Toast.makeText(context, "Mohon maaf, sedang terjadi permasalah jaringan!", Toast.LENGTH_SHORT).show()
                    } else {
                        val message = String(responseBody)

                        if(message.isNotEmpty())
                            if (Util.searchString(message, Constants.UNAUTHENTICATE_MESSAGE)) {
                                AuthOps(context).logout(true, Util.getSharedPref(context, Constants.KEY_PREF_IS_LOGGING_OUT) == null)
                                //(context as AppCompatActivity).finish()
                            } else {
                                callBack.stringCallBack(message)
                                Log.i("response post success", message)
                            }
                    }
                }

                override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                    if(statusCode >= 400){
                        callBack.stringCallBack("Error Bro")
                    }

                }

            })
        } else
            Toast.makeText(context, "Mohon maaf, Anda tidak terkoneksi ke internet!", Toast.LENGTH_SHORT).show()
    }

    fun post(requestParams: RequestParams, endPoint: String, token: String, callBack : CallBack) {
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivity.activeNetworkInfo

        if(activeNetwork != null){
            val connection = AsyncHttpClient()

            connection.addHeader("Accept", "application/json")
            connection.addHeader("Authorization", "Bearer $token")

            // untuk memasukan parameter dari post request
            connection.post(endPoint, requestParams, object : AsyncHttpResponseHandler(){
                override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                    val message = String(responseBody!!)

                    if(message.isNotEmpty())
                        if (Util.searchString(message, Constants.UNAUTHENTICATE_MESSAGE)) {
                            AuthOps(context).logout(true, Util.getSharedPref(context, Constants.KEY_PREF_IS_LOGGING_OUT) == null)
                          //  (context as AppCompatActivity).finish()
                        } else {
                            callBack.stringCallBack(message)
                            Log.i("response post success", message)
                        }
                }

                override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                    if(responseBody != null) {
                        val message = String(responseBody)

                        callBack.stringCallBack(message)
                        Log.i("response post failed", message)

                        if(message.isNotEmpty())
                            if (Util.searchString(message, Constants.UNAUTHENTICATE_MESSAGE)) {
                                AuthOps(context).logout(true, Util.getSharedPref(context, Constants.KEY_PREF_IS_LOGGING_OUT) == null)
                              //  (context as AppCompatActivity).finish()
                            }
                    } else {
                        Toast.makeText(context, "Mohon maaf, Anda tidak terkoneksi ke internet!", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } else
            Toast.makeText(context, "Mohon maaf, Anda tidak terkoneksi ke internet!", Toast.LENGTH_SHORT).show()
    }

    fun get(url: String, token: String, callBack : CallBack) {
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivity.activeNetworkInfo

        if(activeNetwork != null){
            val connection = AsyncHttpClient()

            connection.addHeader("Accept", "application/json")
            connection.addHeader("Authorization", "Bearer $token")

            // untuk memasukan parameter dari post request

            connection.get(url, object : AsyncHttpResponseHandler(){
                override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                    val message = String(responseBody!!)

                    if(message.isNotEmpty())
                        if (Util.searchString(message, Constants.UNAUTHENTICATE_MESSAGE)) {
                            AuthOps(context).logout(true, Util.getSharedPref(context, Constants.KEY_PREF_IS_LOGGING_OUT) == null)
                            //(context as AppCompatActivity).finish()
                        } else {
                            callBack.stringCallBack(String(responseBody))
                            Log.i("response post success", String(responseBody))
                        }
                }

                override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                    if (responseBody != null) {
                        val message = String(responseBody)

                        if (message.isNotEmpty())
                            if (Util.searchString(message, Constants.UNAUTHENTICATE_MESSAGE)) {
                                AuthOps(context).logout(true, Util.getSharedPref(context, Constants.KEY_PREF_IS_LOGGING_OUT) == null)
                              //  (context as AppCompatActivity).finish()
                            } else{
                                callBack.stringCallBack(String(responseBody))
                                Log.i("response post failed", String(responseBody))
                            }
                    } else {
                        Toast.makeText(context,"Mohon maaf, terjadi kesalahan server", Toast.LENGTH_SHORT).show()
                    }
                }

            })
        } else
            Toast.makeText(context, "Mohon maaf, Anda tidak terkoneksi ke internet!", Toast.LENGTH_SHORT).show()
    }


    // ini nanti untuk bind hasil pencarian item
    inner class SearchResult<ListType, Type> {
        var foundList: ArrayList<ListType>? = null
            private set
        var totalFound: Type? = null

        constructor(foundList: ArrayList<ListType>, totalFound: Type) {
            this.foundList = foundList
            this.totalFound = totalFound
        }

        constructor() {}

        fun setSearchList(daftarTemu: ArrayList<ListType>) {
            this.foundList = daftarTemu
        }
    }

    fun getBitmapFromURL(src: String): Bitmap? {
        try {
            val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivity.activeNetworkInfo

            if(activeNetwork != null) {
                val url = URL(src)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input = connection.inputStream
                return BitmapFactory.decodeStream(input)
            } else
                return null
        } catch (e: IOException) {
            // Log exception
            return null
        }

    }

    fun loadImageToImageView(iv: ImageView, ivSizeType: Int, url: String){
        var dim = 500

        when(ivSizeType){
            IMAGE_SIZE_TUMBNAIL_SMALL -> {
                dim = 50
            }

            IMAGE_SIZE_TUMBNAIL_MEDIUM -> {
                dim = 100
            }
        }

        Picasso.get()
            .load(url)
            .resize(dim, dim)
            .centerCrop()
            .into(iv)
    }
}
