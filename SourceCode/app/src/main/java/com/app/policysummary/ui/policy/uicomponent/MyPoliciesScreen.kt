package com.app.policysummary.ui.policy.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.policysummary.ui.policy.viewmodel.PolicyViewModel
import com.app.policysummary.ui.policy.viewmodel.PolicyViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.policysummary.R

@Composable
fun MyPoliciesScreen() {
    val context = LocalContext.current
    val viewModel: PolicyViewModel = viewModel(
        factory = PolicyViewModelFactory(context)
    )

    val policies = viewModel.policies
    val isLoading by viewModel.isLoading
    val query by viewModel.searchQuery
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .filterNotNull()
            .distinctUntilChanged()
            .collectLatest { index ->
                if (index >= policies.lastIndex && !isLoading) {
                    viewModel.loadMore()
                }
            }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = query,
            onValueChange = { viewModel.updateSearchQuery(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 12.dp, end = 12.dp, bottom = 8.dp),
            placeholder = { Text(stringResource(R.string.search_placeholder)) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            shape = RoundedCornerShape(16.dp)
        )

        if (policies.isEmpty() && !isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text =stringResource(R.string.no_policy_found),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
            }
        } else {

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.onBackground)
                    .padding(horizontal = 6.dp, vertical = 6.dp)
                    .fillMaxSize()
            ) {
                items(policies) { policy ->
                    PolicyCard(policy)
                }

                if (isLoading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    MyPoliciesScreen()
}







