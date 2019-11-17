package id.go.surabaya.disperdagin.utilities.dataoperation

import id.go.surabaya.disperdagin.interfaces.CallBack
import id.go.surabaya.disperdagin.models.Commodity
import id.go.surabaya.disperdagin.models.User
import java.io.File

interface BasicOps {
    fun addCommodity(com: Commodity, pic: File?, callBack: CallBack)
    fun getCommodityList(callback: CallBack)
    fun getCommodityDetail(comId: String, callBack: CallBack){}
    fun getCommodityKulakanList(callBack: CallBack)
    fun getTransactionList(callBack: CallBack)
    fun getOrderList(callBack: CallBack){}
    fun showProfileData(user: User, callBack: CallBack){}
    fun getComKindList(callBack: CallBack)
    fun getUnitList(callBack: CallBack)
    fun order(comId: Int, amount: Int, callBack: CallBack){}
    fun confirmOrder(orderId: Int, callBack: CallBack) {}
    fun rejectOrder(orderid: Int, callBack: CallBack) {}
    fun loadOrderDetail(orderid: Int, callBack: CallBack) {}
    fun getOmzet(callBack: CallBack){}
}