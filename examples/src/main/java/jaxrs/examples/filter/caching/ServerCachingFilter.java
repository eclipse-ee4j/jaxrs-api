/*
 * Copyright (c) 2011, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jaxrs.examples.filter.caching;

import java.io.IOException;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

/**
 * ServerCachingFilter class.
 *
 * @author Santiago Pericas-Geertsen
 */
@Provider
@Priority(Priorities.USER)
public class ServerCachingFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Response.ResponseBuilder res = getCachedResponse(requestContext);
        if (res != null) {
            // stop the filter chain
            throw new WebApplicationException(res.build());
        }
    }

    private Response.ResponseBuilder getCachedResponse(ContainerRequestContext requestContext) {
        // implemetation goes here
        return null;
    }
}
