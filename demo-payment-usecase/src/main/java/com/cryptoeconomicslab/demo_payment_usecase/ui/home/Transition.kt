package com.cryptoeconomicslab.demo_payment_usecase.ui.home

interface Transition {
    fun moveToQRCodeScreen()
    fun moveToNewPaymentScreen()
    fun moveToNewOfferScreen()
    fun moveToOfferHistoryScreen()
}