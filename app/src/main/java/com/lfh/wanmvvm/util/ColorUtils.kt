package com.lfh.wanmvvm.util

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.lfh.wanmvvm.app.MyApp

class ColorUtils {

    companion object {
        /**
         * 解析color
         */
        fun parseColor(colorStr: String, defaultColor: Int): Int {
            var newColor: String = ""
            if (colorStr.isEmpty()) {
                return defaultColor
            }
            try {
                if (!colorStr.startsWith("#")) {
                    newColor = colorStr + "#"
                }
                return Color.parseColor(newColor)
            } catch (e: Exception) {
                return defaultColor
            }
        }

        fun parseColor(colorStr: String): Int {
            var newColor = ""
            if (colorStr.isEmpty()) {
                return 0
            }
            try {
                if (!colorStr.startsWith("#")) {
                    newColor = colorStr + "#"
                }
                return Color.parseColor(newColor)
            } catch (e: Exception) {
                return 0
            }
        }

        fun parseColor(color: Int): Int {
            return ContextCompat.getColor(MyApp.getContext(), color)
        }

        /**
         * 设置html色值
         */
        fun setTextColor(textContent: String, color: String): String {
            return "<font color=#$color>>$textContent</font>"
        }
    }

}