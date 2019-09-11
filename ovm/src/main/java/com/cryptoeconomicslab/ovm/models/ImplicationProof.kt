package com.cryptoeconomicslab.ovm.models

import org.web3j.abi.TypeEncoder
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray
import org.web3j.abi.datatypes.DynamicBytes
import org.web3j.abi.datatypes.Uint
import java.math.BigInteger

val String.hexAsByteArray inline get() = this.chunked(2).map { it.toUpperCase().toInt(16).toByte() }.toByteArray()

class ImplicationProof(val implication: Property, val witness: DynamicArray<DynamicBytes>) {
    fun encode(): String {
        val result = StringBuilder()
        var offset: Long = 32 * 2
        result.append(TypeEncoder.encode(Uint(BigInteger.valueOf(offset) )))
        val property = TypeEncoder.encode(DynamicBytes(implication.encode().hexAsByteArray))
        offset += DynamicBytes(property.hexAsByteArray).value.size.toLong()
        result.append(TypeEncoder.encode(Uint(BigInteger.valueOf(offset))))
        result.append(property)
        result.append(TypeEncoder.encode(witness))
        return result.toString()
    }
}
