package io.reflectoring.buckpal.account.application.service

import io.reflectoring.buckpal.account.application.port.inbound.SendMoneyCommand
import io.reflectoring.buckpal.account.application.port.inbound.SendMoneyUseCase
import io.reflectoring.buckpal.common.UseCase
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class SendMoneyService: SendMoneyUseCase {
    override fun sendMoney(command: SendMoneyCommand): Boolean {
        TODO("Not yet implemented $command")
    }
}