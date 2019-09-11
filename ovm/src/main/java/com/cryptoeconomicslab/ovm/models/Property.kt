package com.cryptoeconomicslab.ovm.models

import org.web3j.abi.TypeEncoder
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicBytes
import org.web3j.abi.datatypes.Uint
import java.math.BigInteger

data class Property(val predicate: Address, val input: DynamicBytes) {
    fun encode(): String {
        val result = StringBuilder()
        result.append(TypeEncoder.encode(predicate))
        result.append(TypeEncoder.encode(Uint(BigInteger.valueOf(32 * 2) )))
        result.append(TypeEncoder.encode(input))
        return result.toString()
    }
}
