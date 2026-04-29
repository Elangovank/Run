package com.elango.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.internal.serialize.codecs.core.NodeOwner
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType


val Project.libs
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")