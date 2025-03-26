package com.kiiplan.tekoplan.billing

import android.app.Activity
import android.content.Context
import com.android.billingclient.api.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Gère l'abonnement mensuel via Google Play Billing
class BillingClientManager(private val context: Context) {

    private val _isSubscribed = MutableStateFlow(false)
    val isSubscribed: StateFlow<Boolean> = _isSubscribed

    private val billingClient = BillingClient.newBuilder(context)
        .setListener { _, purchases ->
            handlePurchases(purchases)
        }
        .enablePendingPurchases()
        .build()

    fun startConnection() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(result: BillingResult) {
                if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                    checkSubscriptionStatus()
                }
            }

            override fun onBillingServiceDisconnected() {}
        })
    }

    private fun checkSubscriptionStatus() {
        val params = QueryPurchasesParams.newBuilder()
            .setProductType(BillingClient.ProductType.SUBS)
            .build()

        billingClient.queryPurchasesAsync(params) { _, purchases ->
            handlePurchases(purchases)
        }
    }

    private fun handlePurchases(purchases: List<Purchase>?) {
        _isSubscribed.value = purchases?.any {
            it.products.contains("monthly_premium") &&
                    it.purchaseState == Purchase.PurchaseState.PURCHASED &&
                    !it.isAcknowledged.not()
        } == true
    }

    fun launchSubscription(activity: Activity) {
        val params = QueryProductDetailsParams.newBuilder()
            .setProductList(
                listOf(
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId("monthly_premium")
                        .setProductType(BillingClient.ProductType.SUBS)
                        .build()
                )
            )
            .build()

        billingClient.queryProductDetailsAsync(params) { _, details ->
            val offerToken = details.firstOrNull()
                ?.subscriptionOfferDetails?.firstOrNull()
                ?.offerToken ?: return@queryProductDetailsAsync

            val billingParams = BillingFlowParams.newBuilder()
                .setProductDetailsParamsList(
                    listOf(
                        BillingFlowParams.ProductDetailsParams.newBuilder()
                            .setProductDetails(details.first())
                            .setOfferToken(offerToken)
                            .build()
                    )
                )
                .build()

            billingClient.launchBillingFlow(activity, billingParams)
        }
    }
}
