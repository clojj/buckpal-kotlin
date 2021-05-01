package io.reflectoring.buckpal.account.application.port.inbound

import arrow.core.ValidatedNel
import arrow.core.invalidNel
import arrow.core.validNel
import arrow.core.zip
import java.math.BigInteger

interface SendMoneyUseCase {
    fun sendMoney(command: SendMoneyCommand): String
}

class SendMoneyCommand private constructor(
    val sourceAccountId: Long,
    val targetAccountId: Long,
    val amount: BigInteger
) {

    companion object {
        fun of(sourceAccountId: Long?, targetAccountId: Long?, amount: BigInteger?) =
            sourceAccountId.validateAsAccountId()
                .zip(
                    targetAccountId.validateAsAccountId(),
                    amount.validate()
                )
                { source, target, amount -> SendMoneyCommand(source, target, amount) }

        private fun Long?.validateAsAccountId(): ValidatedNel<String, Long> =
            this?.validNel() ?: "null".invalidNel()

        private fun BigInteger?.validate(): ValidatedNel<String, BigInteger> =
            this?.let { if (it >= BigInteger.TEN) it.validNel() else "must be at least 10 bucks".invalidNel() }
                ?: "null".invalidNel()

    }
}