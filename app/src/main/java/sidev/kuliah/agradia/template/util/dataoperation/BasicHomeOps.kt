package id.go.surabaya.disperdagin.utilities.dataoperation

import android.content.Context
import android.util.Log
import com.loopj.android.http.RequestParams
import id.go.surabaya.disperdagin.interfaces.CallBack
import id.go.surabaya.disperdagin.models.Commodity
import id.go.surabaya.disperdagin.utilities.ApiParams
import id.go.surabaya.disperdagin.utilities.Endpoints
import id.go.surabaya.disperdagin.utilities.ServerUtil
import id.go.surabaya.disperdagin.utilities.Util
import java.io.File

class BasicHomeOps (val ctx: Context) {

    val serverUtil = ServerUtil(ctx)

    val token = Util.decodeBase64(ctx.let { Util.getSharedPref(it, Util.KEY_PREF_USER_TOKEN) }!!)


    fun getLocationList(callBack: CallBack) {
        val endPoint = Endpoints.MAP_LIST

        serverUtil.get(endPoint, token, callBack)
    }

    fun getCarouselList(callBack: CallBack) {
        val endPoint = Endpoints.CAROUSEL_LIST

        serverUtil.get(endPoint, token, callBack)
    }
}