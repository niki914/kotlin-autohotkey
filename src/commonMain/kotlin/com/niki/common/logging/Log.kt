package com.niki.common.logging

import com.niki.config.Config


fun logV(msg: String, tag: String = "") = Log.v(tag, msg)
fun logD(msg: String, tag: String = "") = Log.d(tag, msg)
fun logI(msg: String, tag: String = "") = Log.i(tag, msg)
fun logW(msg: String, tag: String = "") = Log.w(tag, msg)
fun logE(msg: String, tag: String = "", throwable: Throwable? = null) = Log.e(tag, msg, throwable)

fun setLogLevel(level: LogLevel) {
    Log.logLevel = level
}

fun setOnLogCallback(c: OnLogCallback?) = Log.setOnLogCallback(c)

fun interface OnLogCallback {
    fun onLog(level: LogLevel, tag: String, msg: String, throwable: Throwable?)
}

private object Log : Logger(Config.getLogLevel()) {


    private var callback: OnLogCallback? = null

    override fun onLog(level: LogLevel, tag: String, msg: String, throwable: Throwable?) {
        if (Config.shouldPrintToConsole()) {
            println(msg)
        }
        callback?.onLog(level, tag, msg, throwable)
    }

    fun setOnLogCallback(c: OnLogCallback?) {
        callback = c
    }
}