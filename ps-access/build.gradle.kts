/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
plugins {
    java
    kotlin("jvm")
}

repositories {
    jcenter()
}

dependencies {
    implementation(project(":incoming:org.eclipse.passage.lic.api"))
    implementation(project(":incoming:org.eclipse.passage.lic.base"))
    implementation(project(":outgoing:org.eclipse.passage.lic.internal.api"))
    implementation(project(":outgoing:org.eclipse.passage.lic.internal.base"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.springframework:spring-context:5.2.1.RELEASE")

    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
    testImplementation("org.springframework:spring-test:5.2.1.RELEASE")
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).configureEach{
    kotlinOptions.jvmTarget = "1.8"
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
