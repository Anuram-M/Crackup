package com.kumar.crackup.auth

/**
 * Result of validating a single field.
 * Kept as a sealed class (not a nullable String) so the compiler forces
 * every caller to handle both the valid and invalid case explicitly.
 */
sealed class FieldValidation {
    object Valid : FieldValidation()
    data class Invalid(val message: String) : FieldValidation()

    val errorOrNull: String?
        get() = (this as? Invalid)?.message
}

/**
 * Raw input for the whole Create Account form. Plain data — no Android
 * or Compose types — so this and the validator below can be unit tested
 * without an emulator or Robolectric.
 */
data class CreateAccountFormInput(
    val fullName: String,
    val userEmail: String,
    val mobileNumber: String,
    val exam: String,
    val district: String,
    val password: String,
    val confirmPassword: String
)

data class SignInFormInput(
    val userEmail: String,
    val password: String
)

/**
 * Per-field errors for the whole form. Every property is null when that
 * field is valid, so the UI layer can do `errors.email != null` to decide
 * whether to show a red border / helper text under that specific field.
 */
data class CreateAccountFormErrors(
    val fullName: String? = null,
    val userEmail: String? = null,
    val mobileNumber: String? = null,
    val exam: String? = null,
    val district: String? = null,
    val password: String? = null,
    val confirmPassword: String? = null
) {
    val isValid: Boolean
        get() = fullName == null &&
                userEmail == null &&
                mobileNumber == null &&
                exam == null &&
                district == null &&
                password == null &&
                confirmPassword == null
}

data class SignInFormErrors(
    val userEmail: String? = null,
    val password: String? = null
) {
    val isValid: Boolean
        get() = userEmail == null && password == null
}

/**
 * Pure validation logic for the Create Account form.
 * No side effects, no Android dependencies — call validate() with the
 * current field values and read back per-field error messages.
 */
object AuthValidator {

    private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
    private const val MIN_PASSWORD_LENGTH = 8
    private const val MOBILE_LENGTH = 10

    fun validateFullName(value: String): FieldValidation {
        val trimmed = value.trim()
        return when {
            trimmed.isEmpty() -> FieldValidation.Invalid("Enter your full name")
            trimmed.length < 2 -> FieldValidation.Invalid("Name is too short")
            trimmed.any { it.isDigit() } -> FieldValidation.Invalid("Name shouldn't contain numbers")
            else -> FieldValidation.Valid
        }
    }

    fun validateEmail(value: String): FieldValidation {
        val trimmed = value.trim()
        return when {
            trimmed.isEmpty() -> FieldValidation.Invalid("Enter your email")
            !EMAIL_REGEX.matches(trimmed) -> FieldValidation.Invalid("Enter a valid email address")
            else -> FieldValidation.Valid
        }
    }

    fun validateMobileNumber(value: String): FieldValidation {
        val digitsOnly = value.filter { it.isDigit() }
        return when {
            value.isBlank() -> FieldValidation.Invalid("Enter your mobile number")
            digitsOnly.length != MOBILE_LENGTH -> FieldValidation.Invalid("Enter a valid $MOBILE_LENGTH-digit mobile number")
            else -> FieldValidation.Valid
        }
    }

    fun validateExam(value: String): FieldValidation {
        return if (value.isBlank()) {
            FieldValidation.Invalid("Select your target exam")
        } else {
            FieldValidation.Valid
        }
    }

    fun validateDistrict(value: String): FieldValidation {
        return if (value.isBlank()) {
            FieldValidation.Invalid("Select your district")
        } else {
            FieldValidation.Valid
        }
    }

    fun validatePassword(value: String): FieldValidation {
        return when {
            value.isEmpty() -> FieldValidation.Invalid("Enter a password")
            value.length < MIN_PASSWORD_LENGTH -> FieldValidation.Invalid("Password must be at least $MIN_PASSWORD_LENGTH characters")
            value.none { it.isDigit() } -> FieldValidation.Invalid("Add at least one number")
            value.none { !it.isLetterOrDigit() } -> FieldValidation.Invalid("Add at least one symbol")
            else -> FieldValidation.Valid
        }
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): FieldValidation {
        return when {
            confirmPassword.isEmpty() -> FieldValidation.Invalid("Re-enter your password")
            confirmPassword != password -> FieldValidation.Invalid("Passwords don't match")
            else -> FieldValidation.Valid
        }
    }

    /**
     * Validates every field in the form and returns a SignUpFormErrors
     * object. Check `.isValid` on the result to decide whether it's safe
     * to proceed with account creation.
     */
    fun validateSignUp(input: CreateAccountFormInput): CreateAccountFormErrors {
        return CreateAccountFormErrors(
            fullName = validateFullName(input.fullName).errorOrNull,
            userEmail = validateEmail(input.userEmail).errorOrNull,
            mobileNumber = validateMobileNumber(input.mobileNumber).errorOrNull,
            exam = validateExam(input.exam).errorOrNull,
            district = validateDistrict(input.district).errorOrNull,
            password = validatePassword(input.password).errorOrNull,
            confirmPassword = validateConfirmPassword(input.password, input.confirmPassword).errorOrNull
        )
    }

    fun validateSignIn(input: SignInFormInput) : SignInFormErrors {
        return SignInFormErrors(
            userEmail = validateEmail(input.userEmail).errorOrNull,
            password = validatePassword(input.password).errorOrNull
        )
    }
}
