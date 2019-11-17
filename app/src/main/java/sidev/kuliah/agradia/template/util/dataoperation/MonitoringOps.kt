package id.go.surabaya.disperdagin.utilities.dataoperation

import android.content.Context
import android.util.Log
import com.loopj.android.http.RequestParams
import id.go.surabaya.disperdagin.interfaces.CallBack
import id.go.surabaya.disperdagin.models.Commodity
import id.go.surabaya.disperdagin.utilities.*
import java.io.File

class MonitoringOps (val ctx: Context) {

    val serverUtil = ServerUtil(ctx)

    val token = Util.decodeBase64(ctx.let { Util.getSharedPref(it, Util.KEY_PREF_USER_TOKEN) }!!)

    fun getSellerData(callBack: CallBack) {
        val endPoint = Endpoints.MONITORING_SELLER
        serverUtil.get(endPoint, token, callBack)
    }

    fun getMonitoringData(commId: String, chartType: String, dateStart: String, dateEnd: String, callBack: CallBack) {
        val endPoint = "${Endpoints.KOMODITI}/$commId/chart/$chartType"
        val params = RequestParams()

        params.put(ApiParams.REQ_COM_CHART[0], dateStart)
        params.put(ApiParams.REQ_COM_CHART[1], dateEnd)

        serverUtil.post(params, endPoint, token, callBack)
    }
}