package com.example.universalremote

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.ConsumerIrManager

data class DeviceCapabilities(
    val hasIrFeature: Boolean,
    val hasIrEmitter: Boolean,
    val hasWifiFeature: Boolean
)

object DeviceCapabilityDetector {

    fun detect(context: Context): DeviceCapabilities {
        val packageManager = context.packageManager

        val hasIrFeature = packageManager.hasSystemFeature(
            PackageManager.FEATURE_CONSUMER_IR
        )

        val irManager = context.getSystemService(
            Context.CONSUMER_IR_SERVICE
        ) as? ConsumerIrManager

        val hasIrEmitter = hasIrFeature &&
            irManager?.hasIrEmitter() == true

        val hasWifiFeature = packageManager.hasSystemFeature(
            PackageManager.FEATURE_WIFI
        )

        return DeviceCapabilities(
            hasIrFeature = hasIrFeature,
            hasIrEmitter = hasIrEmitter,
            hasWifiFeature = hasWifiFeature
        )
    }
}
