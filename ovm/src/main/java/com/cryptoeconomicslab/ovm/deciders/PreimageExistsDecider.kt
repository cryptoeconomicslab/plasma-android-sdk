package com.cryptoeconomicslab.ovm.deciders


import org.web3j.abi.TypeEncoder
import org.web3j.abi.datatypes.Address
import com.cryptoeconomicslab.ovm.models.Decision

typealias Bytes = String;

data class PreimageExistsDeciderInput(val hash: Bytes)


class PreimageExistsDecider {
    fun decide(input: PreimageExistsDeciderInput): Decision {
        return Decision()
    }
}
