package id.go.surabaya.disperdagin.utilities.dataoperation

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.loopj.android.http.RequestParams
import id.go.surabaya.disperdagin.BuildConfig
import id.go.surabaya.disperdagin.interfaces.CallBack
import id.go.surabaya.disperdagin.models.User
import id.go.surabaya.disperdagin.utilities.*
import org.json.JSONObject
import java.io.File

class AuthOps(val ctx: Context?){

    private val serverUtil = ServerUtil(ctx!!)

    fun login(username: String, password: String, callBack: CallBack){
        val endPoint = Endpoints.LOGIN
        val reqParams = RequestParams()

        reqParams.put(ApiParams.REQ_LOGIN[0], password)
        reqParams.put(ApiParams.REQ_LOGIN[1], 2)
        reqParams.put(ApiParams.REQ_LOGIN[2], BuildConfig.CLIENT_SECRET)
        reqParams.put(ApiParams.REQ_LOGIN[3], username)
        reqParams.put(ApiParams.REQ_LOGIN[4], password)
        reqParams.put(ApiParams.REQ_LOGIN[5], "*")

        serverUtil.post(reqParams, endPoint, callBack)
    }

    fun regFcmToServer(fcmToken: String, serverToken: String, callBack: CallBack){
        val endPoint = Endpoints.FCM_REGISTER_TO_SERVER
        val reqParams = RequestParams()

        reqParams.put(ApiParams.REQ_FCM_TOKEN[0], fcmToken)

        serverUtil.post(reqParams, endPoint, serverToken, callBack)
    }

    fun getUserInfo(token: String, callBack: CallBack){
        val getUrl = Endpoints.USER_INFO

        serverUtil.get(getUrl, token, callBack)
    }

    fun updateUserProfile(user: User, profilePic: File?, callBack: CallBack){
        val endPoint = Endpoints.USER_INFO_UPDATE
        val reqParams = RequestParams()

        if(ctx != null) {
            val token = Util.decodeBase64(ctx.let { Util.getSharedPref(it!!, Util.KEY_PREF_USER_TOKEN) }!!)

            if(user.name != null)
                reqParams.put(ApiParams.REQ_PROFILE_UPDATE[0], user.name)
            if(user.profile?.businessName != null)
                reqParams.put(ApiParams.REQ_PROFILE_UPDATE[1], user.profile?.businessName)
            if(user.profile?.address != null)
                reqParams.put(ApiParams.REQ_PROFILE_UPDATE[2], user.profile?.address)
            if(user.profile?.contact != null)
                reqParams.put(ApiParams.REQ_PROFILE_UPDATE[3], user.profile?.contact)
            if(user.profile?.workingHour != null)
                reqParams.put(ApiParams.REQ_PROFILE_UPDATE[4], user.profile?.workingHour)
            if(user.profile?.village != null)
                reqParams.put(ApiParams.REQ_PROFILE_UPDATE[5], user.profile?.village)
            if(profilePic != null)
                reqParams.put(ApiParams.REQ_PROFILE_UPDATE[6], profilePic)
            if(user.profile?.lat != null)
                reqParams.put(ApiParams.REQ_PROFILE_UPDATE[7], user.profile?.lat)
            if(user.profile?.long != null)
                reqParams.put(ApiParams.REQ_PROFILE_UPDATE[8], user.profile?.long)

            serverUtil.post(reqParams, endPoint, token, callBack)
        } else
            callBack.stringCallBack(Constants.NULL_RESPONSE)
    }

    fun getSubdistrict(callBack: CallBack){
        val getUrl = Endpoints.KEC_LIST
        val token = Util.decodeBase64(ctx.let { Util.getSharedPref(it!!, Util.KEY_PREF_USER_TOKEN) }!!)
        serverUtil.get(getUrl, token, callBack)
    }

    // parameter kedua untuk checking apakah masih proses log out atau tidak Util.getSharedPref(ctx, Constants.KEY_PREF_IS_LOGGING_OUT) == null jika tidak proses apapun
    fun logout(revoked: Boolean, isNotLoggingOut: Boolean){
        val endPoint = Endpoints.LOGOUT
        val reqParams = RequestParams()
        if(ctx != null){
            val encodedToken = Util.getSharedPref(ctx, Util.KEY_PREF_USER_TOKEN)
            if(encodedToken != null){
                if(isNotLoggingOut && !revoked){
                    val token: String = Util.decodeBase64(encodedToken)
                    serverUtil.post(reqParams, endPoint, token, object: CallBack{
                        override fun stringCallBack(message: String) {
                            if(message.isNotEmpty()) {
                                Log.d("Logout", message)
                                if (Util.searchString(message, "code")) {
                                    if (JSONObject(message).getInt("code") == 200) {
                                        showToast("Anda telah keluar dari akun!")
                                        clearAllAccountData()
                                        (ctx as Activity).finish()
                                    }
                                }
                            }
                        }
                    })
                } else{
                    showToast("Mohon maaf, ada orang lain yang masuk dengan akun ini!")
                    showToast("Anda telah keluar dari akun!")
                    clearAllAccountData()
                    (ctx as Activity).finish()
                }
            }
        }
    }

    fun clearAllAccountData(){
        if(ctx != null) {
            Util.setSharedPref(ctx, null, Util.KEY_PREF_USER_TOKEN)
            Util.setSharedPref(ctx, null, Util.KEY_PREF_USER_EMAIL)
            Util.setSharedPref(ctx, null, Util.KEY_PREF_USER_TYPE)
            Util.setSharedPref(ctx, null, Constants.KEY_PREF_USER_PROFILE)
            Util.setSharedPref(ctx, "", Constants.KEY_PREF_IS_LOGGING_OUT)
        }
    }

    fun showToast(message: String){
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show()
    }

}