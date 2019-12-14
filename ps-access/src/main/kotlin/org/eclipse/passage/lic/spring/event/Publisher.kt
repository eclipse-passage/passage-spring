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

import org.eclipse.passage.lic.api.LicensingReporter
import org.eclipse.passage.lic.api.LicensingResult
import org.eclipse.passage.lic.base.SystemReporter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Primary
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
@Primary
class Publisher @Autowired constructor(private val publisher: ApplicationEventPublisher) : LicensingReporter {
    //val logger: Logger TODO: #553877

    @Async
    override fun postResult(result: LicensingResult) {
        sendResult(result)
    }

    override fun logResult(result: LicensingResult) {
        SystemReporter.INSTANCE.logResult(result) //  TODO: #553877
    }

    override fun sendResult(result: LicensingResult) {
        publisher.publishEvent(AccessCycleEvent(result))
    }

}
