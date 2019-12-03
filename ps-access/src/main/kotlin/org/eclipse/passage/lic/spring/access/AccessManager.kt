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

import org.eclipse.passage.lic.api.access.PermissionEmitterRegistry
import org.eclipse.passage.lic.api.conditions.ConditionMinerRegistry
import org.eclipse.passage.lic.internal.api.requirements.RequirementResolverRegistry
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionExecutorRegistry
import org.eclipse.passage.lic.base.access.BaseAccessManager
import org.eclipse.passage.lic.internal.base.access.ConditionTypeProperty
import org.springframework.beans.factory.annotation.Autowired


class AccessManager @Autowired constructor(
        private val conditionMiners: ConditionMinerRegistry,
        private val permissionEmitters: PermissionEmitterRegistry,
        private val requirementResolvers: RequirementResolverRegistry,
        private val restrictionExecutors: RestrictionExecutorRegistry) : BaseAccessManager() {
    init {
        pullConditionMinerRegistry()
        pullPermissionEmitters()
        pullRequirementResolvers()
        pullRestrictionExecutors()
    }

    private fun pullConditionMinerRegistry() = super.bindConditionMinerRegistry(conditionMiners)

    private fun pullPermissionEmitters() =
            permissionEmitters.supportedConditionTypes.forEach { type: String ->
                super.bindPermissionEmitter(permissionEmitters.getPermissionEmitter(type),
                        ConditionTypeProperty(type).map())
            }

    private fun pullRequirementResolvers() =
            requirementResolvers.resolvers().forEach { super.bindRequirementResolver(it) }

    private fun pullRestrictionExecutors() =
            restrictionExecutors.executors().forEach { super.bindRestrictionExecutor(it) }

}
