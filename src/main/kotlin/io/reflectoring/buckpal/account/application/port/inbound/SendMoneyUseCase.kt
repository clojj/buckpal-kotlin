package io.reflectoring.buckpal.account.application.port.inbound

import java.math.BigInteger

interface SendMoneyUseCase {
    fun sendMoney(command: SendMoneyCommand): Boolean
}

data class SendMoneyCommand(val sourceAccountId: Long, val targetAccountId: Long, val amount: BigInteger)