package com.app.policysummary.ui.policy.repo

import android.content.Context
import android.util.Log
import com.app.policysummary.ui.policy.model.Policy
import com.app.policysummary.utils.Constants.POLICY_JSON
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PolicyRepository(private val context: Context) {

    fun loadPoliciesFromAssets(): List<Policy> {

        return try {
            val json = context.assets.open(POLICY_JSON).bufferedReader().use { it.readText() }
            Gson().fromJson(json, object : TypeToken<List<Policy>>() {}.type)
        } catch (e: Exception) {
            Log.e("Exception",e.printStackTrace().toString())
            emptyList()
        }
    }
}
