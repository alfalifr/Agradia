package id.go.surabaya.disperdagin.utilities


object ApiParams {

    /**
     * AUTHENTICATION
     */

    val REQ_LOGIN = arrayOf(
        "grant_type",
        "client_id",
        "client_secret",
        "username",
        "password",
        "scope"
    )

    val REQ_FCM_TOKEN = arrayOf(
        "fcm_token"
    )

    /**
     * ini adalah key parameter untuk DATA RETRIEVING
     */
    val REQ_USER_DATA = arrayOf(
        "username"
    )

    val REQ_COMMODITY_LIST = arrayOf(
        "username"
    )

    val REQ_COMMODITY_DETAIL = arrayOf(
        "id_commodity_tokel"
    )

    val REQ_COMMODITY_LIST_KOPERASI = arrayOf(
        "username"
    )

    val RES_COMMODITY_LIST_TOKEL = arrayOf(
        "id_commodity_tokel",
        "name",
        "img_url",
        "category",
        "price",
        "unit",
        "amount"
    )

    val RES_COMMODITY_LIST_KOPERASI = arrayOf(
        "id_commodity_koperasi",
        "name",
        "img_url",
        "category",
        "price",
        "unit",
        "amount"
    )


    val RES_COMMODITY_DETAIL = arrayOf(
        "commodity_owner",
        "name",
        "category",
        "price",
        "unit",
        "amount"
    )

    val RES_COMMODITY_DETAIL_KOPERASI = arrayOf(
        "commodity_owner",
        "name",
        "category",
        "price",
        "unit",
        "amount"
    )

    val RES_USER_DATA = arrayOf(
        "name",
        "role",
        "username",
        "email",
        "phone",
        "address"
    )


    /**
     * ini adalah key parameter untuk DATA POSTING
     */
    val REQ_TOKEL_ADD_COM = arrayOf(
        "nama_komoditi",
        "id_jenis_komoditi",
        "harga_komoditi",
        "satuan_jual",
        "jumlah_stok",
        "id_satuan",
        "image"
    )

    // kedepannya bisa berkelanjutann ORDER_LINE
    val REQ_ORDER = arrayOf(
        "id_komoditi[0]",
        "kuantitas[0]"
    )

    val RES_DATA_POSTING = arrayOf(
        "code",
        "body"
    )

    val REQ_PROFILE_UPDATE = arrayOf(
        "nama",
        "nama_pemilik",
        "alamat",
        "nomor_hp",
        "jam_operasional",
        "id_kelurahan",
        "image",
        "lat",
        "long"
    )

    val REQ_COM_UPDATE = arrayOf(
        "nama_komoditi",
        "id_jenis_komoditi",
        "harga_komoditi",
        "id_satuan",
        "jumlah_stok",
        "image"
    )

    val REQ_COM_CHART = arrayOf(
        "start",
        "finish"
    )

}


