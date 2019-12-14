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
package org.eclipse.passage.lic.spring.permission

import org.eclipse.passage.lic.api.access.AccessEvents
import org.eclipse.passage.lic.api.access.FeaturePermission
import org.eclipse.passage.lic.base.LicensingResults
import org.eclipse.passage.lic.internal.base.permission.BasePermissionObservatory
import org.eclipse.passage.lic.internal.base.permission.LimitedPermission
import org.eclipse.passage.lic.internal.base.permission.PermissionObservatory
import org.eclipse.passage.lic.internal.base.permission.observatory.CheckSchedule
import org.eclipse.passage.lic.spring.event.AccessCycleEvent
import org.eclipse.passage.lic.spring.event.Listener
import org.eclipse.passage.lic.spring.event.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
@Primary
class SpringPermissionObservatory @Autowired constructor(private val publisher: Publisher)
    : PermissionObservatory, Listener {

    private val implementation = BasePermissionObservatory(CheckSchedule(), this::expired)

    init {
        implementation.open()
    }

    // todo: listen new event from pa-dev which is triggered when the fueature under licensing is -no-linger-used-, call forget then
    override fun onApplicationEvent(event: AccessCycleEvent) {
        if (AccessEvents.CONDITIONS_EVALUATED != event.topic()) return
        @Suppress("UNCHECKED_CAST")
        implementation.watch(event.data() as Collection<FeaturePermission>)
    }

    private fun expired(expired: Collection<LimitedPermission>) {
        publisher.postResult(LicensingResults.createEvent(AccessEvents.PERMISSIONS_EXPIRED, expired))
    }

}
