package com.app.policysummary.ui.policy.uicomponent

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.policysummary.R


@Composable
fun BottomBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val items = listOf(
        stringResource(id = R.string.dashboard),
        stringResource(id = R.string.my_policies),
        stringResource(id = R.string.claims),
        stringResource(id = R.string.profile)
    )
    val icons = listOf(
        R.drawable.ic_tab_home,
        R.drawable.ic_health_policy,
        R.drawable.ic_claim,
        R.drawable.ic_tab_user
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 4.dp
    ) {
        items.forEachIndexed { index, label ->
            val isSelected = selectedTab == index

            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(index) },
                icon = {
                    Icon(
                        painter = painterResource(id = icons[index]),
                        contentDescription = label,
                        tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.White,
                        modifier = Modifier.size(if (isSelected) 20.dp else 24.dp)
                    )
                },
                label = {
                    Text(
                        text = label,
                        color = Color.White,
                        fontSize = 11.sp,
                        style = MaterialTheme.typography.labelSmall

                    )
                },
                alwaysShowLabel = true
            )
        }
    }
}

