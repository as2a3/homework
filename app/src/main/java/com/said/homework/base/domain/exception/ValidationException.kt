package com.said.homework.base.domain.exception

import java.util.*

class ValidationException(val errors: HashMap<String, ValidationErrorType>) : AppException(null)