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

import java.io.IOException;
import org.takes.facets.fallback.FbChain;
import org.takes.facets.fallback.FbStatus;
import org.takes.facets.fallback.TkFallback;
import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.TkFork;
import org.takes.http.Exit;
import org.takes.http.FtCli;
import org.takes.misc.Opt;
import org.takes.rs.RsHtml;
import org.takes.rs.RsText;

/**
 * Project entrance.
 *
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 * @checkstyle MagicNumberCheck (500 lines)
 */
public final class Main {

    /**
     * Ctor.
     */
    private Main() {
    }

    /**
     * Entrance.
     *
     * @param args Arguments
     * @throws IOException If fails
     */
    public static void main(final String... args) throws IOException {
        new FtCli(
            new TkFallback(
                new TkFork(
                    new FkRegex("/robots\\.txt", ""),
                    new FkRegex("/", new TkIndex()),
                    new FkRegex("/service/(?<key>[^/]+)/link", new TkLink())
                ),
                new FbChain(
                    new FbStatus(404, new RsText("sorry, page is absent")),
                    new FbStatus(405, new RsText("this method is not allowed here")),
                    req -> new Opt.Single<>(
                        new RsHtml("oops, something went terribly wrong!")
                    )
                )
            ),
            args
        ).start(Exit.NEVER);
    }
}
