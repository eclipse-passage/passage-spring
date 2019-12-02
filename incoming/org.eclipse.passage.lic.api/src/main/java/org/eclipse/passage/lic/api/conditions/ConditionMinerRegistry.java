/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.lic.api.conditions;

import java.util.Map;

import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.LicensingResult;

/**
 * Contract interface for a registry of {@link ConditionMiner}s.
 *
 * @see ConditionMiner
 * @see LicensingCondition
 * @since 0.4.0
 */
public interface ConditionMinerRegistry {

    /**
     * Returns all registered {@link ConditionMiner}s.
     *
     * @return {@link Iterable} aggregate of {@link ConditionMiner}s. Can be empty. Cannot be {@code null}
     * @see #registerConditionMiner(ConditionMiner, Map)
     * @since 0.4.0
     */
    Iterable<ConditionMiner> getConditionMiners();

    /**
     * Registers the {@code conditionMiner} to make it available for a registry client.
     *
     * @param conditionMiner miner to be registered
     * @param properties     - reserved for metadata
     * @since 0.4.0
     */
    void registerConditionMiner(ConditionMiner conditionMiner, Map<String, Object> properties);

    /**
     * Removes the {@code conditionMiner} from the registry.
     * After removal the {@code conditionMiner} will no longer present in {@link #getConditionMiners} results
     *
     * @param conditionMiner the miner to be forgotten
     * @param properties     reserved for metadata
     * @see #getConditionMiners
     * @since 0.4.0
     */
    void unregisterConditionMiner(ConditionMiner conditionMiner, Map<String, Object> properties);

    /**
     * To be extracted to a separate service and deprecated here: #552752 (https://bugs.eclipse.org/bugs/show_bug.cgi?id=552752)
     *
     * @since 0.4.0
     */
    LicensingResult importConditions(String source, LicensingConfiguration configuration);

    /**
     * Physical source used by the miner to quarry conditions.
     * <p>
     * To be extracted from the interface and deprecated here: #552753 (https://bugs.eclipse.org/bugs/show_bug.cgi?id=552753)
     *
     * @param miner to be examined for a target
     * @since 0.4.0
     */
    String getConditionMinerTarget(ConditionMiner miner);
}
