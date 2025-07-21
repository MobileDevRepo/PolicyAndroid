package com.app.policysummary.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.policysummary.R
import com.app.policysummary.ui.policy.viewmodel.PolicyViewModel
import com.app.policysummary.ui.policy.viewmodel.PolicyViewModelFactory


@Composable
fun HomeScreen(
    onTotalPoliciesClick: () -> Unit,
    onClaimedPoliciesClick: () -> Unit
) {
    val context = LocalContext.current
    val viewModel: PolicyViewModel = viewModel(factory = PolicyViewModelFactory(context))
    val policyCount = viewModel.allPolicies.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PolicyStatCard(
                iconResId = R.drawable.ic_health_policy,
                title = stringResource(R.string.total_policies),
                count = policyCount,
                modifier = Modifier.weight(1f),
                onClick = onTotalPoliciesClick
            )
            PolicyStatCard(
                iconResId = R.drawable.ic_claim,
                title = stringResource(R.string.claimed_policies),
                count = 0,
                modifier = Modifier.weight(1f),
                onClick = onClaimedPoliciesClick
            )
        }
    }
}

@Composable
fun PolicyStatCard(
    iconResId: Int,
    title: String,
    count: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier.aspectRatio(1f),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = title,
                tint = Color.White,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .height(48.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                fontSize = 15.sp,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$count",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}





