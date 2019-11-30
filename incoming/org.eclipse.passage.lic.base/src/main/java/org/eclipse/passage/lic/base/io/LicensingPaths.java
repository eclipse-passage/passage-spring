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
package org.eclipse.passage.lic.base.io;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.passage.lic.api.LicensingConfiguration;

public final class LicensingPaths {

	public static final String FOLDER_LICENSING_BASE = ".passage"; //$NON-NLS-1$

	public static final String EXTENSION_LICENSE_DECRYPTED = ".lic"; //$NON-NLS-1$
	public static final String EXTENSION_LICENSE_ENCRYPTED = ".licen"; //$NON-NLS-1$
	public static final String EXTENSION_PRODUCT_PUBLIC = ".pub"; //$NON-NLS-1$

	private LicensingPaths() {
		// block
	}

	public static Path resolveBasePath(URL url) {
		File file = new File(url.getPath());
		Path path = Paths.get(file.getPath());
		return path.resolve(FOLDER_LICENSING_BASE);
	}

	public static Path resolveConfigurationPath(URL url, LicensingConfiguration configuration) {
		Path base = resolveBasePath(url);
		return resolveConfigurationPath(base, configuration);
	}

	public static Path resolveConfigurationPath(Path from, LicensingConfiguration configuration) {
		Path basePath = from;
		if (configuration == null) {
			return basePath;
		}
		String product = configuration.getProductIdentifier();
		if (product == null) {
			return basePath;
		}
		Path productPath = basePath.resolve(product);
		String version = configuration.getProductVersion();
		if (version == null) {
			return productPath;
		}
		return productPath.resolve(version);
	}

	public static String composeFileName(LicensingConfiguration configuration, String extension) {
		String product = null;
		String version = null;
		if (configuration != null) {
			product = configuration.getProductIdentifier();
			version = configuration.getProductVersion();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(product).append('_').append(version).append(extension);
		return sb.toString();
	}

}
