package io.reflectoring.buckpal

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import io.reflectoring.buckpal.archunit.HexagonalArchitecture
import org.junit.jupiter.api.Test

internal class DependencyRuleTests {
    @Test
    fun validateAccountContextArchitecture() {
        HexagonalArchitecture.boundedContext("io.reflectoring.buckpal.account")
            .withDomainLayer("domain")
            .withAdaptersLayer("adapter")
            ?.incoming("inbound.web")
            ?.outgoing("outbound.persistence")
            ?.and()
            ?.withApplicationLayer("application")
            ?.services("service")
            ?.incomingPorts("port.inbound")
            ?.outgoingPorts("port.outbound")
            ?.and()
            ?.withConfiguration("configuration")
            ?.check(
                ClassFileImporter()
                    .importPackages("io.reflectoring.buckpal..")
            )
    }

    @Test
    fun testPackageDependencies() {
        ArchRuleDefinition.noClasses()
            .that()
            .resideInAPackage("io.reflectoring.reviewapp.domain..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("io.reflectoring.reviewapp.application..")
            .check(
                ClassFileImporter()
                    .importPackages("io.reflectoring.reviewapp..")
            )
    }
}