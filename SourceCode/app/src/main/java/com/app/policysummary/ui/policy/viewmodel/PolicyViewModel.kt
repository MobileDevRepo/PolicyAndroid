package com.app.policysummary.ui.policy.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.policysummary.ui.policy.model.Policy
import com.app.policysummary.ui.policy.repo.PolicyRepository
import com.app.policysummary.utils.Constants.DISPLAY_DATA_SIZE
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PolicyViewModel(private val repository: PolicyRepository) : ViewModel() {

    private val _policies = mutableStateListOf<Policy>()
    val policies: List<Policy> get() = _policies

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> get() = _searchQuery

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private var currentIndex = 0
    var allPolicies: List<Policy> = emptyList()

    init {
        loadInitialPolicies()
    }

    private fun loadInitialPolicies() {
        allPolicies = repository.loadPoliciesFromAssets()
        _policies.clear()
        _policies.addAll(allPolicies.take(DISPLAY_DATA_SIZE))
        currentIndex = _policies.size
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        val filtered = if (query.isBlank()) {
            allPolicies.take(currentIndex)
        } else {
            allPolicies.filter {
                it.policyNumber.toString().contains(query, ignoreCase = true) ||
                        it.policyName.contains(query, ignoreCase = true)
            }
        }
        _policies.clear()
        _policies.addAll(filtered)
    }

    fun loadMore() {
        if (currentIndex < allPolicies.size && _searchQuery.value.isBlank()) {
            _isLoading.value = true
            viewModelScope.launch {
                delay(1500)
                val next = allPolicies.drop(currentIndex).take(DISPLAY_DATA_SIZE)
                _policies.addAll(next)
                currentIndex += next.size
                _isLoading.value = false
            }
        }
    }

}

