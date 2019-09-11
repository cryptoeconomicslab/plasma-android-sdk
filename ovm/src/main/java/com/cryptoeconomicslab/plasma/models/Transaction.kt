package com.cryptoeconomicslab.plasma.models

import org.web3j.abi.TypeEncoder
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicBytes
import org.web3j.abi.datatypes.Int

data class Transaction(
    val predicate: Address,
    val block_number: Int,
    val range: Range,
    val params: DynamicBytes
)