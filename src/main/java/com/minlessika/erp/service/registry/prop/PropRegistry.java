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

import com.minlessika.erp.service.registry.api.Registry;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Registry from a properties file.
 *
 * @since 0.1
 */
@SuppressWarnings(
    {
        "PMD.AvoidCatchingGenericException",
        "PMD.AvoidThrowingRawExceptionTypes",
        "PMD.AvoidFileStream"
    }
)
public final class PropRegistry implements Registry {

    /**
     * Property filename.
     */
    private static final String PROP_FILENAME = "config.properties";

    /**
     * Properties.
     */
    private final Properties prop;

    /**
     * Ctor.
     */
    public PropRegistry() {
        this(PropRegistry.openConfigFile());
    }

    /**
     * Ctor.
     *
     * @param file File
     */
    public PropRegistry(final File file) {
        this.prop = PropRegistry.loadConfigFile(file);
    }

    @Override
    public URL read(final String key) throws Exception {
        return new URL(this.prop.getProperty(key));
    }

    /**
     * Load properties content file.
     *
     * @param file Properties file
     * @return Properties
     */
    private static Properties loadConfigFile(final File file) {
        final Properties prop = new Properties();
        try (InputStream input = new FileInputStream(file)) {
            prop.load(input);
        // @checkstyle IllegalCatchCheck (1 line)
        } catch (final Exception exe) {
            throw new RuntimeException(exe);
        }
        return prop;
    }

    /**
     * Get default properties file.
     *
     * @return File
     */
    private static File openConfigFile() {
        final Path path = Paths.get(
            System.getProperty("user.dir"), PropRegistry.PROP_FILENAME
        );
        final File file = path.toFile();
        if (!file.exists()) {
            try {
                try (
                    InputStream in = Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream(PropRegistry.PROP_FILENAME)
                ) {
                    Files.copy(in, path);
                }
            } catch (final IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return file;
    }
}
