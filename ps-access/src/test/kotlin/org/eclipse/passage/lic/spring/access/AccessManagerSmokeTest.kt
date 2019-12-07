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
package org.eclipse.passage.lic.spring.access

import org.eclipse.passage.lic.api.LicensingResult
import org.eclipse.passage.lic.spring.access.fakes.FakeLicensingConfiguration
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

@ExtendWith(SpringExtension::class)
@SpringJUnitConfig(TestConfig::class)
class AccessManagerSmokeTest @Autowired constructor(private val accessManager: AccessManager) {

    @Test
    fun conditions() = assertFalse(accessManager.extractConditions(FakeLicensingConfiguration()).any())

    @Test
    fun permissions() = assertFalse(accessManager.evaluateConditions(FakeLicensingConfiguration(), setOf()).any())

    @Test
    fun requirements() {
        val requirements = accessManager.resolveRequirements(FakeLicensingConfiguration())
        assertEquals(
            1,
            requirements.count()
        ) // a bit unexpected, yah? See #557992 (https://bugs.eclipse.org/bugs/show_bug.cgi?id=557992) for details
        with(requirements.iterator().next()) {
            assertEquals(featureIdentifier, FakeLicensingConfiguration().product)
            //assertEquals(featureVersion, FakeLicensingConfiguration().version) ---  Uncomment when #557992 is up and running
            assertEquals(requirementSource, AccessManager::class.java.name)
        }
    }

    @Test
    fun restrictions() =
        assertFalse(accessManager.examinePermissons(FakeLicensingConfiguration(), setOf(), setOf()).any())

    @Test
    fun execution() = with(accessManager.executeRestrictions(FakeLicensingConfiguration(), setOf())) {
        assertEquals(LicensingResult.OK, code)
    }

}
