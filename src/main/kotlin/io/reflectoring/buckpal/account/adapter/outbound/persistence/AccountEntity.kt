package io.reflectoring.buckpal.account.adapter.outbound.persistence

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "ACCOUNT_ENTITY")
@Entity
class AccountEntity {
    @Column(name = "ID", nullable = false)
    @Id
    var id: Long? = null
}