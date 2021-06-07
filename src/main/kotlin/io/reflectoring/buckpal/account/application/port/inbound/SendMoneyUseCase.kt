package io.reflectoring.buckpal.account.application.port.inbound

import arrow.core.*
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
            allValidations(
                sourceAccountId.validateAsAccountId(),
                targetAccountId.validateAsAccountId(),
                amount.validate()
            ) { source, target, amount -> SendMoneyCommand(source, target, amount) }

        private fun Long?.validateAsAccountId(): ValidatedNel<String, Long> =
            this?.validNel() ?: "null".invalidNel()

        private fun BigInteger?.validate(): ValidatedNel<String, BigInteger> =
            this?.let { if (it >= BigInteger.TEN) it.validNel() else "must be at least 10 bucks".invalidNel() }
                ?: "null".invalidNel()

    }
}

fun <E, A, B, C, R> allValidations(
    validated1: ValidatedNel<E, A>,
    validated2: ValidatedNel<E, B>,
    validated3: ValidatedNel<E, C>,
    f: (A, B, C) -> R
) =
    validated1.zip(validated2, validated3) { a, b, c -> f(a, b, c) }


fun <ERROR, Any> zipValidatedNel(validated: Nel<ValidatedNel<ERROR, Any>>, f: (Nel<Any>) -> Any) {
    if (validated.size > 10) TODO()
    else with(validated) {
        when (size) {
            3 -> first().zip(this[1]) { a, b -> f(Nel(a, listOf(b))) }
            else -> TODO()
        }
    }
}