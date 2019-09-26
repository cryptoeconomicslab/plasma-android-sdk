package com.cryptoeconomicslab.demo_payment_usecase.ui.scan_qr_code

interface Transition {
    fun finishWithResult(text: String)
}