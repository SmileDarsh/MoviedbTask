package com.max.moviedbtask.person.presentation.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.max.moviedbtask.R
import com.max.moviedbtask.core.utils.AppPermission
import com.max.moviedbtask.core.utils.SharedPreferencesManager.getImageUrl
import com.max.moviedbtask.core.utils.loadUrl
import com.max.moviedbtask.core.utils.savePhoto
import kotlinx.android.synthetic.main.activity_person_image.*

/**
 * Created by µðšţãƒâ ™ on 29/10/2020.
 *  ->
 */
class PersonImageActivity : AppCompatActivity() {
    private lateinit var mAppPermission: AppPermission

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_image)

        mAppPermission = AppPermission(this)
        ivImage.loadUrl(getImageUrl())

        tvSaveImage.setOnClickListener { mAppPermission.addRequestPermission() }
    }

    override fun onBackPressed() {
        finishAfterTransition()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        mAppPermission.onRequestPermissions(requestCode, grantResults) { success ->
            if (success)
                ivImage.savePhoto {
                    Toast.makeText(this@PersonImageActivity, it, Toast.LENGTH_LONG).show()
                    finishAfterTransition()
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}