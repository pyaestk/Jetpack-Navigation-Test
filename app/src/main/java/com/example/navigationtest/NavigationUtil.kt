package com.example.navigationtest

import android.net.Uri
import android.os.Parcelable
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import kotlinx.serialization.json.Json

object NavigationUtil {

    inline fun <reified T: Parcelable> parcelableNavType(): NavType<T> {
        return object : NavType<T>(isNullableAllowed = true){
            override fun put(bundle: SavedState, key: String, value: T){
                bundle.putParcelable(key, value)
            }

            override fun get(bundle: SavedState, key: String): T? {
                return bundle.getParcelable(key) as? T
            }

            override fun parseValue(value: String): T {
                return Json.decodeFromString(Uri.decode(value))
            }

        }
    }
}