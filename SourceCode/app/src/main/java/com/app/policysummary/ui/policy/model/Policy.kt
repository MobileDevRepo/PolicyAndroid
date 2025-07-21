package com.app.policysummary.ui.policy.model

import com.google.gson.annotations.SerializedName

data class Policy(
    @SerializedName("Policy Name") val policyName: String,
    @SerializedName("Policy Number") val policyNumber: Int,
    @SerializedName("Policy Status") val policyStatus: String,
    @SerializedName("Next Premium Due") val nextPremiumDue: String,
    @SerializedName("Start Date") val startDate: String,
    @SerializedName("Maturity Date") val maturityDate: String,
    @SerializedName("Sum Assured") val sumAssured: Int,
    @SerializedName("Premium Frequency") val premiumFrequency: String,
    @SerializedName("Last Premium Paid") val lastPremiumPaid: String,
    @SerializedName("Next Premium Amount") val nextPremiumAmount: Int
)

