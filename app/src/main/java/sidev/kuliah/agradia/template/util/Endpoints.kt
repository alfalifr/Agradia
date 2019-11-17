package id.go.surabaya.disperdagin.utilities

import id.go.surabaya.disperdagin.BuildConfig

object Endpoints {
    val ROOT = BuildConfig.ENDPOINT_ROOT
    val KOMODITI  = ROOT + "/api/v1/komoditi"
    val MAP_LIST  = ROOT + "/api/v1/maps"
    val KEC_LIST = ROOT + "/api/v1/kelurahan"
    val CAROUSEL_LIST  = ROOT + "/api/v1/artikel"
    val KOMODITI_KULAKAN_LIST  = ROOT + "/api/v1/kulakan/komoditi"
    val TOKEL_KOMODITI_ADD = ROOT + "/api/v1/komoditi"
    val TRANSACTION_LIST = ROOT + "/api/v1/transaksi"
    val ORDER = ROOT + "/api/v1/kulakan/order"
    val USER_INFO = ROOT + "/api/v1/user"
    val USER_INFO_UPDATE = ROOT + "/api/v1/user/profile"
    val FCM_REGISTER_TO_SERVER = ROOT + "/api/v1/user/fcm-token"
    val LOGIN = ROOT + "/oauth/token"
    val LOGOUT = ROOT + "/api/v1/revoke"
    val UNIT = ROOT + "/api/v1/satuan"
    val OMZET = ROOT + "/api/v1/omzet"
    val COM_KIND = ROOT + "/api/v1/jenis-komoditi"
    val MONITORING_SELLER = ROOT + "/api/v1/monitor/komoditi"
}