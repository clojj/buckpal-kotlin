package io.reflectoring.buckpal.common

import javax.validation.ConstraintViolationException
import javax.validation.Validation
import javax.validation.Validator

abstract class SelfValidating<T> {
    protected val validator: Validator = Validation.buildDefaultValidatorFactory().validator

    /**
     * Evaluates all Bean Validations on the attributes of this
     * instance.
     */
    protected inline fun <reified T> validateSelf() {
        val violations = validator.validate(this)
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
    }
}