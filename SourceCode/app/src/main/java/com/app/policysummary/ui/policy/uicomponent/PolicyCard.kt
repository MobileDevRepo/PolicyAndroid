package com.app.policysummary.ui.policy.uicomponent

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.policysummary.R
import com.app.policysummary.ui.policy.model.Policy

@Composable
fun PolicyCard(policy: Policy) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp),
        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .animateContentSize(animationSpec = tween(500))
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.policy_no)+" ${policy.policyNumber}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )

                PolicyStatusIcon(status = policy.policyStatus)
            }



            VerticalDivider(
                thickness = 1.dp,
                color = Color.LightGray,
                modifier = Modifier.padding(vertical = 8.dp)
            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconWithLabel(
                    icon = painterResource(id = R.drawable.ic_user),
                    label = stringResource(id = R.string.name),
                    value = policy.policyName,
                    modifier = Modifier.weight(1f)
                )
                IconWithLabel(
                    icon = painterResource(id = R.drawable.ic_treasure),
                    label = stringResource(R.string.sum_insured),
                    value = "₹${policy.sumAssured}",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconWithLabel(
                    icon = painterResource(id = R.drawable.ic_calendar),
                    label = stringResource(R.string.valid_till),
                    value = policy.nextPremiumDue,
                    modifier = Modifier.weight(1f)
                )
                IconWithLabel(
                    icon = painterResource(id = R.drawable.ic_transfer_money),
                    label = stringResource(R.string.next_premium),
                    value = "₹${policy.nextPremiumAmount}",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ExpandableIconButton(expanded = expanded) {
                    expanded = !expanded
                }
            }

            if (expanded) {

                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                PolicyDetailItem(stringResource(R.string.next_premium_due), policy.nextPremiumDue)
                PolicyDetailItem(stringResource(R.string.start_date), policy.startDate)
                PolicyDetailItem(stringResource(R.string.maturity_date), policy.maturityDate)
                PolicyDetailItem(stringResource(R.string.last_premium_paid), policy.lastPremiumPaid)
            }
        }
    }
}

@Composable
fun PolicyStatusIcon(status: String) {
    val icon = if (status == "Active") R.drawable.ic_health_policy else R.drawable.ic_lapsed
    val color = if (status == "Active") Color(0xFF4CAF50) else MaterialTheme.colorScheme.secondary

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = stringResource(R.string.policy_status),
            modifier = Modifier.size(24.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = status,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = color
        )
    }
}

@Composable
fun IconWithLabel(
    icon: Painter,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.Top, modifier = modifier) {
        Icon(
            painter = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(30.dp)
                .padding(end = 8.dp)
        )
        Column {
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                fontFamily = FontFamily.Default
            )
        }
    }
}

@Composable
fun ExpandableIconButton(expanded: Boolean, onClick: () -> Unit) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = tween(300),
        label = "expand_arrow_rotation"
    )

    TextButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_open_down),
            contentDescription = if (expanded) "Collapse" else "Expand",
            modifier = Modifier
                .size(20.dp)
                .rotate(rotationAngle),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun PolicyDetailItem(label: String, value: String, color: Color = Color.Black) {
    Text(
        text = "$label: $value",
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = color,
        fontFamily = FontFamily.SansSerif
    )
}






