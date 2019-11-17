package id.go.surabaya.disperdagin.utilities

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.R.attr.path
import android.app.Activity
import android.content.Context
import android.content.Intent
import id.go.surabaya.disperdagin.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.min


class BitmapUtil{
    //menggunakan Gallery Intent
    companion object{
        val PICK_IMAGE_GALLERY_REQUEST= 1

        fun pickImageGallery(act: Activity, requestCode: Int= PICK_IMAGE_GALLERY_REQUEST){
            val intent = Intent()
            // Show only images, no videos or anything else
            intent.type= "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            // Always show the chooser (if there are multiple options available)
            act.startActivityForResult(Intent.createChooser(intent, "Select Picture"), requestCode)
        }

        @Deprecated("Gak dipake lagi bro", ReplaceWith("pickImageGallery()"))
        fun bacaGambar(pathOrg: String): Bitmap {
            var u = 1
            val pathFile = "$path/ori/$pathOrg"
            val pathFoto: String
            var file = File("$pathFile/Foto$u.jpg")

            while (file.exists()) {
                u++
                file = File("$pathFile/Foto$u.jpg")
            }
            pathFoto = pathFile + "/Foto" + (u - 1) + ".jpg"
            file = File(pathFoto)

            return BitmapFactory.decodeFile(pathFoto)
        }

        fun bacaGambar(c: Context, resId: Int): Bitmap{
            return BitmapFactory.decodeResource(c.getResources(), resId)
        }

        //crop
        fun pictSquare(bm: Bitmap, size: Int): Bitmap {
            val bmResize= resizePict(bm, size)

            val pjgAwal= bmResize.width
            val lbrAwal= bmResize.height

            if(pjgAwal == lbrAwal)
                return Bitmap.createBitmap(bmResize, 0, 0, pjgAwal, lbrAwal)

            val selisihPjg= (pjgAwal -size).absoluteValue
            val selisihLbr= (lbrAwal -size).absoluteValue

            var xStart= selisihPjg/2
            var yStart= selisihLbr/2

            var sizeNew = size

            if(xStart + size > bmResize.width){
                xStart = 0
                sizeNew = bmResize.width
            }

            if(yStart + size > bmResize.height){
                yStart = 0
                sizeNew = bmResize.height
            }

            return Bitmap.createBitmap(bmResize, xStart, yStart, sizeNew, sizeNew) //--> crop tengah
        }
        fun resizePict(bm: Bitmap, size: Int): Bitmap{
            var sizeMin= bm.height
            var sizeMax= bm.width

            if(sizeMax == sizeMin)
                Bitmap.createScaledBitmap(bm, size, size, false)

            var max= "width"
            if(bm.width < bm.height){
                sizeMin= bm.width
                sizeMax= bm.height
                max= "height"
            }

            val ratioSizeMin= size/sizeMin.toDouble()
            val sizeMaxNew= sizeMax *ratioSizeMin

            var widthNew= sizeMaxNew.toInt()
            var heightNew= size
            if(max == "height"){
                widthNew= size
                heightNew= sizeMaxNew.toInt()
            }

            return Bitmap.createScaledBitmap(bm, widthNew, heightNew, false)
        }

        fun savePict(bm: Bitmap, pathFile: String,
                     fileName: String= DateUtil.timestamp()): File? {
            val fileNameExt= fileName +".jpg"
            val pathFile= File(pathFile)
            val file = File("$pathFile/$fileNameExt")
            try {
                pathFile.mkdirs()
                file.createNewFile()

                val bos = ByteArrayOutputStream()
                bm.compress(Bitmap.CompressFormat.JPEG, 100, bos)
                val bitmapdata = bos.toByteArray()

                val fos = FileOutputStream(file)
                fos.write(bitmapdata)
                fos.flush()
                fos.close()
                return file
            } catch (e: Exception) {
                throw e
            }
        }
    }
}