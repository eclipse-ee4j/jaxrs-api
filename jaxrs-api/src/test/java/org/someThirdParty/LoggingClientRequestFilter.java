/*
 * Copyright (c) 2018 IBM and Contributors to the Eclipse Foundation
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */
package org.someThirdParty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientBuilderListener;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Response;

/**
 * Used for {@link javax.ws.rs.client.ClientBuilderListener} unit tests.
 *
 * @author Andy McCright
 * @since 2.2
 */
public class LoggingClientRequestFilter implements ClientRequestFilter {

    public final static List<String> REQUESTS = new ArrayList<>();

    @Override
    public void filter(ClientRequestContext context) throws IOException {
        REQUESTS.add(context.getUri().toString());
        context.abortWith(Response.ok().build());
    }
}
