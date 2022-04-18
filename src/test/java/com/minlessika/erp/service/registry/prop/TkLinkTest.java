/*
 * Copyright (c) 2018-2022 Minlessika
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to read
 * the Software only. Permissions is hereby NOT GRANTED to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.minlessika.erp.service.registry.prop;

import com.minlessika.erp.service.registry.web.TkLink;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.takes.facets.fork.RqRegex;
import org.takes.rs.RsPrint;
import wtf.g4s8.hamcrest.json.JsonHas;
import wtf.g4s8.hamcrest.json.StringIsJson;

/**
 * Test case for {@link TkLink}.
 *
 * @since 0.1
 */
public final class TkLinkTest {

    @Test
    void readsLink() throws Exception {
        MatcherAssert.assertThat(
            new RsPrint(
                new TkLink().act(
                    new RqRegex.Fake("/service/(?<key>[^/]+)/link", "/service/MINLESSIKA/link")
                )
            ).printBody(),
            new StringIsJson.Object(
                new JsonHas("link", "https://www.minlessika.com")
            )
        );
    }
}
