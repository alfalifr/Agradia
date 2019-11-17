package id.go.surabaya.disperdagin.utilities.dataoperation

import android.content.Context
import android.util.Log
import com.loopj.android.http.RequestParams
import id.go.surabaya.disperdagin.interfaces.CallBack
import id.go.surabaya.disperdagin.models.Commodity
import id.go.surabaya.disperdagin.utilities.*
import java.io.File

class BasicComOps (val ctx: Context) : BasicOps {

    val serverUtil = ServerUtil(ctx)

    val token = Util.decodeBase64(ctx.let { Util.getSharedPref(it, Util.KEY_PREF_USER_TOKEN) }!!)

    override fun addCommodity(com: Commodity, pic: File?, callBack: CallBack) {
        val endPoint = Endpoints.TOKEL_KOMODITI_ADD
        val params = RequestParams()

        params.put(ApiParams.REQ_TOKEL_ADD_COM[0], com.name)
        params.put(ApiParams.REQ_TOKEL_ADD_COM[1], com.kind._id?.toInt())
        params.put(ApiParams.REQ_TOKEL_ADD_COM[2], com.price?.toInt())
//        params.put(ApiParams.REQ_TOKEL_ADD_COM[3], com.unit.name!!.toInt())
        params.put(ApiParams.REQ_TOKEL_ADD_COM[4], com.stock?.toInt())
        params.put(ApiParams.REQ_TOKEL_ADD_COM[5], com.unit._id?.toInt())
        if(pic != null)
            params.put(ApiParams.REQ_TOKEL_ADD_COM[6], pic)

        serverUtil.post(params, endPoint, token, callBack)
    }

    override fun getCommodityList(callback: CallBack) {
        val endPoint = Endpoints.KOMODITI
      //  Log.d("get com list", endPoint)
        serverUtil.get(endPoint, token, callback)
    }

    override fun getCommodityDetail(comId: String, callBack: CallBack) {
        val endPoint = Endpoints.KOMODITI + "/" + comId

        serverUtil.get(endPoint, token, callBack)
    }

    override fun getCommodityKulakanList(callBack: CallBack) {
        val endPoint = Endpoints.KOMODITI_KULAKAN_LIST
        Log.d("get com list", endPoint)
        serverUtil.get(endPoint, token, callBack)
    }

    override fun getTransactionList(callBack: CallBack) {
        val endPoint = Endpoints.TRANSACTION_LIST
        Log.d("get transaksi list", endPoint)
        serverUtil.get(endPoint, token, callBack)
    }

    override fun getOrderList(callBack: CallBack) {
        val endPoint = Endpoints.ORDER
        Log.d("get order list", endPoint)
        serverUtil.get(endPoint, token, callBack)
    }

    override fun getComKindList(callBack: CallBack) {
        val endPoint = Endpoints.COM_KIND

        serverUtil.get(endPoint, token, callBack)
    }

    override fun getUnitList(callBack: CallBack) {
        val endPoint = Endpoints.UNIT

        serverUtil.get(endPoint, token, callBack)
    }


    override fun order(comId: Int, amount: Int, callBack: CallBack) {
        val endPoint = Endpoints.ORDER
        val requests = RequestParams()

        requests.put(ApiParams.REQ_ORDER[0], comId)
        requests.put(ApiParams.REQ_ORDER[1], amount)

        serverUtil.post(requests, endPoint, token, callBack)
    }

    override fun confirmOrder(orderId: Int, callBack: CallBack) {
        val endPoint = "${Endpoints.ORDER}/$orderId/confirm"
        val requests = RequestParams()

        serverUtil.post(requests, endPoint, token, callBack)
    }

    override fun rejectOrder(orderId: Int, callBack: CallBack) {
        val endPoint = "${Endpoints.ORDER}/$orderId/reject"
        val requests = RequestParams()

        serverUtil.post(requests, endPoint, token, callBack)
    }

    override fun loadOrderDetail(orderId: Int, callBack: CallBack) {
        val endPoint = "${Endpoints.ORDER}/$orderId"

        serverUtil.get(endPoint, token, callBack)
    }

    fun updateCommodity(com: Commodity, pic: File?, callBack: CallBack) {
        val endPoint = "${Endpoints.KOMODITI}/${com.id}"
        val requests = RequestParams()
        var nothingChanged = true

        if(com.name != null) {
            requests.put(ApiParams.REQ_COM_UPDATE[0], com.name)
            Log.d("edit kom", "name")
            nothingChanged = false
        }
        if(com.kind._id != null){
            requests.put(ApiParams.REQ_COM_UPDATE[1], com.kind._id)
            Log.d("edit kom", "kind id" + com.kind._id)
            nothingChanged = false
        }
        if(com.price != null) {
            requests.put(ApiParams.REQ_COM_UPDATE[2], com.price!!)
            Log.d("edit kom", "price")
            nothingChanged = false
        }
        if(com.unit._id != null) {
            requests.put(ApiParams.REQ_COM_UPDATE[3], com.unit._id)
            Log.d("edit kom", "unit id " + com.unit._id)
            nothingChanged = false
        }
        if(com.stock != null) {
            requests.put(ApiParams.REQ_COM_UPDATE[4], com.stock)
            Log.d("edit kom", "stock")
            nothingChanged = false
        }
        if(pic != null){
            requests.put(ApiParams.REQ_COM_UPDATE[5], pic)
            Log.d("edit kom", "pic")
            nothingChanged = false
        }

        if(nothingChanged)
            callBack.stringCallBack(Constants.RESPONSE_NOTHING_CHANGED)
        else
            serverUtil.post(requests, endPoint, token, callBack)
    }

    override fun getOmzet(callBack: CallBack) {
        val endPoint = Endpoints.OMZET
        serverUtil.get(endPoint, token, callBack)
    }
}