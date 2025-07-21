package com.app.policysummary.ui.policy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.policysummary.ui.policy.repo.PolicyRepository

@Suppress("UNCHECKED_CAST")
class PolicyViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = PolicyRepository(context)
        return PolicyViewModel(repository) as T
    }
}
