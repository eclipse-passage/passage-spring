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
}

repositories {
    jcenter()
}

dependencies {
    implementation(project(":incoming:org.eclipse.passage.lic.api"))
}

// workaround for #557991 - [Passage] rework (facade) i18n machine in common passage bundles
sourceSets.forEach{
    it.resources{
        srcDir(file("src/main/java"))
    }
}
