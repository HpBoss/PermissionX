package com.permissionx.guolindev.patch

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import java.util.UUID

/**
 * @Author : noah
 * @createTime : 2025/4/1
 */
class DelegateActivity : FragmentActivity() {
    private lateinit var uuid: UUID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setBackgroundDrawableResource(android.R.color.transparent)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT

        uuid = intent.getSerializableExtra("uuid") as UUID

        supportFragmentManager.setFragmentResultListener(
            PermissionDelegateHolder.REQUEST_KEY,
            this,
        ) { _, bundle ->
            val result = bundle.getBoolean(PermissionDelegateHolder.RESULT_KEY)
            if (result) release()
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        //execute permission request
        PermissionDelegateHolder.holder[uuid]?.invoke(this)
    }

    private fun release() {
        PermissionDelegateHolder.holder.remove(uuid)
        finish()
    }

}