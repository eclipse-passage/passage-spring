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
package org.eclipse.passage.lic.spring.access.fakes

import org.eclipse.passage.lic.api.LicensingConfiguration

data class FakeLicensingConfiguration(val product: String, val version: String) : LicensingConfiguration {
    constructor() : this("HappyHalloween", "31.10.2019")

    override fun getProductVersion() = version

    override fun getProductIdentifier() = product
}
