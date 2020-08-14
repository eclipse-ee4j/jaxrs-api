/*
 * Copyright (c) 2010, 2021 Oracle and/or its affiliates. All rights reserved.
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

package jakarta.ws.rs.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.ext.RuntimeDelegate;

public class NewCookieTest {

    @BeforeEach
    public void setUp() throws Exception {
        RuntimeDelegate.setInstance(new RuntimeDelegateStub());
    }

    @AfterEach
    public void tearDown() throws Exception {
        RuntimeDelegate.setInstance(null);
    }

    /**
     * Test of valueOf method, of class NewCookie.
     */
    @Test
    public void testCtor() {
        Cookie c = new Cookie("name", "value");
        NewCookie nc = new NewCookie(c);
        assertEquals(nc.getName(), c.getName());
        try {
            nc = new NewCookie.Builder((Cookie)null).build();
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
        try {
            nc = new NewCookie.Builder((Cookie) null).comment("comment").maxAge(120).secure(true).build();
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testSameSite() {

        NewCookie sameSiteOmit = new NewCookie.Builder("name").value("value").path("/").domain("localhost").maxAge(0)
                .build();
        assertNull(sameSiteOmit.getSameSite());

        NewCookie sameSiteNull = new NewCookie.Builder("name").value("value").path("/").domain("localhost").maxAge(0)
                .sameSite(null).build();
        assertNull(sameSiteNull.getSameSite());

        NewCookie sameSiteNone = new NewCookie.Builder("name").value("value").path("/").domain("localhost").maxAge(0)
                .sameSite(SameSite.NONE).build();
        assertEquals(NewCookie.SameSite.NONE, sameSiteNone.getSameSite());

        NewCookie sameSiteLax = new NewCookie.Builder("name").value("value").path("/").domain("localhost").maxAge(0)
                .sameSite(SameSite.LAX).build();
        assertEquals(NewCookie.SameSite.LAX, sameSiteLax.getSameSite());

        NewCookie sameSiteStrict = new NewCookie.Builder("name").value("value").path("/").domain("localhost").maxAge(0)
                .sameSite(SameSite.STRICT).build();
        assertEquals(NewCookie.SameSite.STRICT, sameSiteStrict.getSameSite());

    }

}
