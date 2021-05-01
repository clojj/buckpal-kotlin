package io.reflectoring.buckpal.account.adapter.inbound.web

import io.reflectoring.buckpal.account.application.port.inbound.SendMoneyCommand
import io.reflectoring.buckpal.account.application.port.inbound.SendMoneyUseCase
import io.reflectoring.buckpal.common.WebAdapter
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
internal class SendMoneyController(val sendMoneyUseCase: SendMoneyUseCase) {

    @PostMapping(path = ["/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}"])
    fun sendMoney(
        @PathVariable("sourceAccountId") sourceAccountId: Long?,
        @PathVariable("targetAccountId") targetAccountId: Long?,
        @PathVariable("amount") amount: Long
    ): String {
        SendMoneyCommand.of(sourceAccountId, targetAccountId, amount.toBigInteger())
            .fold({ throw UnsupportedOperationException(it.joinToString(", ")) }) {
                return sendMoneyUseCase.sendMoney(it)
            }
    }
}