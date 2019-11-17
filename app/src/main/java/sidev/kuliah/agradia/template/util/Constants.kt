package id.go.surabaya.disperdagin.utilities

import id.go.surabaya.disperdagin.BuildConfig

object Constants {
    val USER_TYPE_DISDAG = 8
    val USER_TYPE_KOPERASI = 2
    val USER_TYPE_TOKEL = 3
    val USER_TYPE_SWK = 4
    val USER_TYPE_UKM = 5
    val USER_TYPE_DISTRIBUTOR = 6
    val USER_TYPE_GUDANG = 6
    val USER_TYPE_OPD = 7

    // tipe untuk list commodity
    val COM_TYPE_TOKEL_MINE = 0
    val COM_TYPE_TOKEL_BUY = 1
    val COM_TYPE_KOPERASI_MINE = 2
    val COM_TYPE_KOPERASI_BUY = 3
    val COM_TYPE_DISTRIBUTOR_MINE = 4

    // tipe monitoring
    val MONITORING_TYPE_OPD = 1
    val MONITORING_TYPE_DISDAG = 2

    //tipe list transaksi
    val TRANSAC_TYPE_FRAGMENT = 0
    val TRANSAC_TYPE_CHILDFRAGMENT = 1
    val TRANSAC_TYPE_ACTIVITY = 2

   // untuk sorting list
    val SORT_COMMODITY_NAME = 0
    val SORT_COMMODITY_STOCK = 1
    val SORT_TRANSACTION_NAME = 0
    val SORT_TRANSACTION_PRICE = 1
    val SORT_TRANSACTION_DATE = 2

    // untuk filter lsit
    val FILTER_COMMODITY_AVAILABLE = 0
    val FILTER_COMMODITY_EMPTY = 1
    val FILTER_TRANSACTION_UNCONFIRM = 0
    val FILTER_TRANSACTION_REJECTED = 1
    val FILTER_TRANSACTION_APPROVED = 2
    val FILTER_TRANSACTION_BUY = 3
    val FILTER_TRANSACTION_SELL = 4

    // commodity detail id_order bar
    val ACTBAR_COM_DETAIL = 0
    val ACTBAR_COM_DETAIL_ORDER = 1

    // status transaksi
    val STATUS_TRANSACTION_UNCONFIRMED = 1
    val STATUS_TRANSACTION_REJECTED = 2
    val STATUS_TRANSACTION_APPROVED = 3

    // tipe transaksi
    val TYPE_TRANSACTION_BUY = 1
    val TYPE_TRANSACTION_SELL = 2

    val UNAUTHENTICATE_MESSAGE = "Unauthenticated"
    val NULL_RESPONSE = "{\"code\" : 101}"
    val NULL_RESPONSE_CODE = 101

    val DETAIL_TYPE_REGULAR = 0
    val DETAIL_TYPE_KOPERASI = 1
    val DETAIL_TYPE_DISTRIBUTOR = 2

    val ORDERING_TYPE_FULLY_AVAILABLE = 0
    val ORDERING_TYPE_PARTIAL_AVAILABLE = 1
    val ORDERING_TYPE_UNAVAILABLE = 2
    val ORDERING_TYPE_ZERO = 3

    val MAP_TYPE_KEY = "map_type_key"
    val MAP_TYPE_KOPERASI = 411
    val MAP_TYPE_TOKEL = 412
    val MAP_TYPE_DISTRIBUTOR = 413

    val MAP_KEYS = arrayOf("admin", "koperasi", "kelontong", "swk", "ukm", "distributor")

    val INTENT_LAT_ADD = "intent_lat_add"
    val INTENT_LONG_ADD = "intent_long_add"
    val INTENT_RQ_LOC_ADD = 193

    // data untuk informasi
    val KEY_PREF_USER_PROFILE = "pref_user_profile"
    val KEY_PREF_IS_LOGGING_OUT = "is_logging_out"
    val USER_PROFILE_TYPE_OWN = 2345
    val USER_PROFILE_TYPE_OTHER = 5432

    // untuk response json
    val RESPONSE_NOTHING_CHANGED = "{\"code\" : 201}"

    //untuk notifikasi
    val NOTIF_TAG = "FcmMessagingService"
    val NOTIF_TITLE = "title"
    val NOTIF_BODY = "body"
    val NOTIF_TYPE = "type"
    val NOTIF_ID_ORDER = "id_order"

    // extra keys
    val EXTRA_TRANSACTION_ITEM = "extra_transaction_item"
}