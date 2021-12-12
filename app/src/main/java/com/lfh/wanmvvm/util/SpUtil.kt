package com.lfh.wanmvvm.util

import android.content.Context
import android.text.TextUtils
import android.util.Base64
import com.lfh.wanmvvm.app.MyApp
import java.io.*


/**
 * Sp工具类封装
 * author:胖仔
 */
object SpUtil {
    private const val CONFIG_NAME = "config"

    private val sp = MyApp.getContext().getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE)

    /**
     * 获得存储的默认bool值
     */
    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sp.getBoolean(key, defaultValue)
    }

    fun setBoolean(key: String,value:Boolean){
        sp.edit().putBoolean(key,value).apply()
    }


    fun removeKey(key: String): Boolean {
        return sp.edit().remove(key).commit()
    }


    fun setObject(key: String, value: Any?) {
        if (value == null) {
            return
        }
        if (value !is Serializable) {
            return
        }
        var baos: ByteArrayOutputStream? = null
        var oos: ObjectOutputStream? = null
        try {

            baos = ByteArrayOutputStream()
            oos = ObjectOutputStream(baos)
            oos.writeObject(value)
            val temp = String(Base64.encode(baos.toByteArray(), Base64.DEFAULT))
            sp.edit().putString(key, temp).apply()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (oos != null && baos != null) {
                CloseUtils.closeIO(oos, baos)
            }
        }

    }

    fun getObject(key: String): Any? {
        var `object`: Any? = null
        var bais: ByteArrayInputStream? = null
        var ois: ObjectInputStream? = null

        val temp = sp.getString(key, "")
        if (!TextUtils.isEmpty(temp)) {
            try {
                bais = ByteArrayInputStream(Base64.decode(temp!!.toByteArray(), Base64.DEFAULT))
                ois = ObjectInputStream(bais)
                `object` = ois.readObject()
            } catch (ignored: Exception) {

            } finally {
                if (ois != null && bais != null) {
                    CloseUtils.closeIO(ois, bais)
                }
            }
        }
        return `object`
    }




}