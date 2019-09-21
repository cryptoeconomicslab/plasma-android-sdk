package com.cryptoeconomicslab.demo_payment_usecase.ui.offer_history


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.cryptoeconomicslab.demo_payment_usecase.repository.offer.OfferRepositoryMock

/**
 * A simple [Fragment] subclass.
 */
class OfferHistoryFragment : Fragment() {

    companion object {
        fun getFragment() = OfferHistoryFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val repository = OfferRepositoryMock()

        val view = inflater.inflate(R.layout.fragment_offer_history, container, false)

        view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            adapter = OfferViewAdapter(context, repository.getOfferHistories())
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        return view
    }
}
