package id.go.surabaya.disperdagin.utilities.json

import org.json.JSONArray
import org.json.JSONObject
// JSON CHECKER
// by sidev Si ITS 2017
class JObjs{

    var jsonObj: JSONObject

    constructor(jsonString: String) {
        jsonObj = JSONObject(jsonString)
    }

    constructor(json: JSONObject) {
        jsonObj = json
    }

    object TAGS {
        val STRING_DEFAULT = ""
        val INT_DEFAULT = 0
        val LONG_DEFAULT = 0
        val DOUBLE_DEFAULT = 0.0
        val JSONOBJECT_DEFAULT = JObjs("{}")
    }

    fun getString(key: String, defaultValue : String = "") : String{
        return if(jsonObj.has(key) && !jsonObj.isNull(key)) jsonObj.getString(key) else defaultValue
    }

    fun getInt(key: String, defaultValue: Int = 0) : Int {
        return if(jsonObj.has(key) && !jsonObj.isNull(key)) jsonObj.getInt(key) else defaultValue
    }

    fun getLong(key: String, defaultValue: Long = 0) : Long {
        return if(jsonObj.has(key) && !jsonObj.isNull(key)) jsonObj.getLong(key) else defaultValue
    }

    fun getDouble(key: String, defaultValue: Double = 0.0) : Double {
        return if(jsonObj.has(key) && !jsonObj.isNull(key)) jsonObj.getDouble(key) else defaultValue
    }

    // untuk mendapatkan json object
    fun getJSONObject(key: String, defaultValue: JObjs = JObjs("{}")) : JObjs {
        return if(jsonObj.has(key) && !jsonObj.isNull(key)) JObjs(jsonObj.getJSONObject(key)) else return defaultValue
    }

    // untuk mendapatkan json array
    fun getJSONArray(key: String, defaultValue: JSONArray = JSONArray("[]")) : JSONArray {
        return if(jsonObj.has(key) && !jsonObj.isNull(key)){
            jsonObj.getJSONArray(key)
        }else return defaultValue
    }
    // ini untuk konvert ke string
    override fun toString(): String{
        return jsonObj.toString(0)
    }
}