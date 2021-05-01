package io.reflectoring.buckpal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class BuckpalKotlinApplication

fun main(args: Array<String>) {
    runApplication<BuckpalKotlinApplication>(*args)
}
