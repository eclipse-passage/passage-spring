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
import org.eclipse.passage.lic.api.LicensingResult
import org.eclipse.passage.lic.api.access.PermissionEmitter
import org.eclipse.passage.lic.api.access.PermissionEmitterRegistry
import org.eclipse.passage.lic.api.conditions.ConditionMiner
import org.eclipse.passage.lic.api.conditions.ConditionMinerRegistry
import org.eclipse.passage.lic.api.requirements.RequirementResolver
import org.eclipse.passage.lic.api.restrictions.RestrictionExecutor
import org.eclipse.passage.lic.internal.api.requirements.RequirementResolverRegistry
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionExecutorRegistry
import org.springframework.stereotype.Component

/*
* Very very temporary fakes pack
* */
@Component
class CMs : ConditionMinerRegistry {
    override fun getConditionMinerTarget(miner: ConditionMiner?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getConditionMiners(): MutableIterable<ConditionMiner> = mutableListOf()


    override fun importConditions(source: String?, configuration: LicensingConfiguration?): LicensingResult {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerConditionMiner(conditionMiner: ConditionMiner?, properties: MutableMap<String, Any>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unregisterConditionMiner(conditionMiner: ConditionMiner?, properties: MutableMap<String, Any>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

@Component
class PEs : PermissionEmitterRegistry {
    override fun getConditionTypeDescription(conditionType: String?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPermissionEmitter(conditionType: String?): PermissionEmitter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPermissionEmitters(): MutableIterable<PermissionEmitter> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSupportedConditionTypes() = mutableListOf<String>()

    override fun getDefaultConditionType(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getConditionTypeName(conditionType: String?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

@Component
class RRs : RequirementResolverRegistry {
    override fun resolvers() = mutableListOf<RequirementResolver>()
}

@Component
class REs : RestrictionExecutorRegistry {
    override fun executors() = mutableListOf<RestrictionExecutor>()
}
