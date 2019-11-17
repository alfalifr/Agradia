package id.go.surabaya.disperdagin.utilities

import android.content.Context
import android.os.Environment
import id.go.surabaya.disperdagin.R

class EnvUtil{
    companion object{
        fun projectDir(c: Context): String{
            var dir= Environment.getExternalStorageDirectory().absolutePath
            dir += "/" +c.resources.getString(R.string.app_name)
            return dir
        }
        fun projectTempDir(c: Context): String{
            return projectDir(c) +"/temp"
        }
    }
}