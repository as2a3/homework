package com.said.homework.base.presentation.di.scope

import javax.inject.Scope

/**
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the fragment to be memorized in the
 * correct component.
 */
@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class PerFragment 