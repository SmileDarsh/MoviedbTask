package com.max.moviedbtask.core.custom

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.max.moviedbtask.R
import kotlinx.android.synthetic.main.custom_toolbar_layout.view.*

/**
 * Created by µðšţãƒâ ™ on 29/10/2020.
 *  ->
 */
class CustomToolbar : ConstraintLayout {
    private lateinit var mTitle: TextView
    private lateinit var mBackButton: ImageButton

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val v = View.inflate(context, R.layout.custom_toolbar_layout, this)
        val attr = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar)
        try {
            val text = attr.getString(R.styleable.CustomToolbar_title)
            val backVisibility = attr.getBoolean(R.styleable.CustomToolbar_back_visibility, false)
            val backgroundColor =
                attr.getColor(R.styleable.CustomToolbar_toolbar_background_color, 0)

            mTitle = v.tvTitle
            mBackButton = v.ibnBack

            mTitle.text = text

            setBackgroundColor(if (backgroundColor == 0) Color.WHITE else backgroundColor)
            mBackButton.setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    (context as Activity).finishAfterTransition()
                } else {
                    (context as Activity).finish()
                }
            }

            if (backVisibility)
                backHide()
        } finally {
            attr.recycle()
        }
    }

    fun setTitle(title: String) {
        mTitle.text = title
    }

    fun setTitle(@StringRes title: Int) {
        mTitle.text = context.getString(title)
    }

    fun backButton(): ImageButton = mBackButton

    private fun backHide() {
        mBackButton.visibility = View.GONE
    }
}