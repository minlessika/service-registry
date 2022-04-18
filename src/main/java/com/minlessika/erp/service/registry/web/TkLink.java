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
package com.minlessika.erp.service.registry.web;

import com.minlessika.erp.service.registry.prop.PropRegistry;
import java.net.URL;
import javax.json.Json;
import org.takes.Response;
import org.takes.facets.fork.RqRegex;
import org.takes.facets.fork.TkRegex;
import org.takes.rs.RsJson;
import org.takes.rs.RsPrettyJson;

/**
 * Take that reads a link.
 *
 * @since 0.1
 */
public final class TkLink implements TkRegex {

    @Override
    public Response act(final RqRegex req) throws Exception {
        final URL url = new PropRegistry().read(
            req.matcher().group("key")
        );
        return new RsPrettyJson(
            new RsJson(
                Json.createObjectBuilder()
                    .add("link", url.toString())
                    .build()
            )
        );
    }
}
