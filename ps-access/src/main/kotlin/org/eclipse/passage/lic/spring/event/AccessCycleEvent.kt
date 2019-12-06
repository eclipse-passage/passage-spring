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
package org.eclipse.passage.lic.spring.event

import org.eclipse.passage.lic.api.LicensingEvents
import org.eclipse.passage.lic.api.LicensingResult
import org.springframework.context.ApplicationEvent

class AccessCycleEvent(private val origin: LicensingResult) : ApplicationEvent(origin.source) {

    fun topic() = origin.getAttachment(LicensingEvents.PROPERTY_TOPIC)

    fun data() = origin.getAttachment(LicensingEvents.PROPERTY_DATA)

}
