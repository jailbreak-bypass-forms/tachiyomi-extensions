package eu.kanade.tachiyomi.extension.vi.blogtruyen

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import kotlin.system.exitProcess

class BlogTruyenUrlActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pathSegments = intent?.data?.pathSegments
        if (pathSegments != null && pathSegments.size > 1) {
            val id = "/${pathSegments[0]}/${pathSegments[1]}"
            try {
                startActivity(
                    Intent().apply {
                        action = "eu.kanade.tachiyomi.SEARCH"
                        putExtra("query", "${BlogTruyen.PREFIX_ID_SEARCH}$id")
                        putExtra("filter", packageName)
                    }
                )
            } catch (e: ActivityNotFoundException) {
                Log.e("BlogTruyenUrlActivity", e.toString())
            }
        } else {
            Log.e("BlogTruyenUrlActivity", "Could not parse URI from intent $intent")
        }

        finish()
        exitProcess(0)
    }
}
