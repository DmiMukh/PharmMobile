package com.flyview.core.android

import android.content.Context
import android.content.Intent
import android.content.IntentFilter

interface AndroidIntent {

    val intentFilter: IntentFilter
    fun onBroadcastReceive(context: Context?, intent: Intent?)
}