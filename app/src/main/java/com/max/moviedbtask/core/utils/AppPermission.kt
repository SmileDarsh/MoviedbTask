package com.max.moviedbtask.core.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.max.moviedbtask.R

/**
 * Created by µðšţãƒâ ™ on 29/10/2020.
 *  ->
 */
const val STORAGE_REQUEST = 350

class AppPermission(private val mActivity: Activity) {

    fun addRequestPermission() {
        ActivityCompat.requestPermissions(
            mActivity,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ), STORAGE_REQUEST
        )
    }

    fun onRequestPermissions(
        requestCode: Int,
        grantResults: IntArray,
        callback: (success: Boolean) -> Any
    ) {
        if (requestCode == STORAGE_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.checkSelfPermission(
                        mActivity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) ==
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        mActivity,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    callback(true)
                }
            } else {
                callback(false)
                Toast.makeText(
                    mActivity,
                    mActivity.getString(R.string.permissions_denied),
                    Toast.LENGTH_LONG
                ).show();
            }
        }
    }
}