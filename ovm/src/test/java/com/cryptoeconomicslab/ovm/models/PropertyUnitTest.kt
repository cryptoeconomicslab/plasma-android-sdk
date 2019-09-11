package com.cryptoeconomicslab.ovm.models

import org.junit.Test
import org.junit.Assert.*
import com.cryptoeconomicslab.ovm.models.Property;
import org.web3j.abi.datatypes.Address
import org.web3j.abi.datatypes.DynamicBytes

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PropertyUnitTest {
    @Test
    fun encode_property() {
        val encodeTest = """
            |0000000000000000000000001234567800123456780012345678001234567800
            |0000000000000000000000000000000000000000000000000000000000000040
            |0000000000000000000000000000000000000000000000000000000000000001
            |0100000000000000000000000000000000000000000000000000000000000000
            """.trimMargin().replace("\n", "")
        val property = Property(Address("0x1234567800123456780012345678001234567800"), DynamicBytes(byteArrayOf(0x01)))
        print(property.encode())
        print(encodeTest)
        assertEquals(property.encode(), encodeTest)
    }

}
