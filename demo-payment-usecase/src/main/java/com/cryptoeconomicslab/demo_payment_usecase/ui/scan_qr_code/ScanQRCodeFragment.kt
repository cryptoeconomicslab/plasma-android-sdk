package com.cryptoeconomicslab.demo_payment_usecase.ui.scan_qr_code


import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

/**
 * A simple [Fragment] subclass.
 */
@RuntimePermissions
class ScanQRCodeFragment(val transition: Transition) : Fragment() {

    lateinit var qrCodeReaderView: QRCodeReaderView
    lateinit var addressText: TextView
    lateinit var errorText: TextView

    companion object {
        fun getFragment(transition: Transition) = ScanQRCodeFragment(transition)

        const val ADDRESS_TEXT_LENGTH = 7
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scan_qrcode, container, false)

        qrCodeReaderView = view.findViewById(R.id.qrcode_view)
        addressText = view.findViewById(R.id.address_text)

        qrCodeReaderView.apply {
            setBackCamera()
            setAutofocusInterval(2000)
            setQRDecodingEnabled(true)
            setOnQRCodeReadListener { text, _ ->
                if (validateQRCode(text)) {
                    addressText.text = text
                    transition.finishWithResult(text)
                }
            }
        }

        errorText = view.findViewById(R.id.error_text)

        startQRCodeCameraWithPermissionCheck()

        return view
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    fun startQRCodeCamera() {
        qrCodeReaderView.startCamera()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    private fun validateQRCode(address: String): Boolean {
        // FIXME: add validate logic
        return if (address.length < ADDRESS_TEXT_LENGTH) {
            errorText.visibility = View.VISIBLE
            false
        } else {
            errorText.visibility = View.GONE
            true
        }
    }


}
