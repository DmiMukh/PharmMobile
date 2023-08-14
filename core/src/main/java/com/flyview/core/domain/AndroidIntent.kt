package com.flyview.core.domain

import android.content.Context
import android.content.Intent
import android.content.IntentFilter

interface AndroidIntent {

    val intentFilter: IntentFilter
    fun onBroadcastReceive(context: Context?, intent: Intent?)
}