/*
 * Copyright 2014 Higher Frequency Trading
 *
 * http://www.higherfrequencytrading.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.openhft.chronicle.logger.log4j2;

import net.openhft.chronicle.logger.ChronicleLogWriter;
import net.openhft.chronicle.logger.ChronicleLogWriters;
import net.openhft.chronicle.logger.VanillaLogAppenderConfig;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

import java.io.IOException;

@Plugin(
    name        = "TextVanillaChronicle",
    category    = "Core",
    elementType = "appender",
    printObject = true)
public class TextVanillaChronicleAppender extends AbstractTextChronicleAppender {

    private static final long serialVersionUID = 1L;
    
    private final VanillaLogAppenderConfig config;

    public TextVanillaChronicleAppender(
        final String name, final Filter filter, final String path, VanillaLogAppenderConfig config) {
        super(name, filter, path);
        this.config = config != null ? config : new VanillaLogAppenderConfig();
    }

    @Override
    protected ChronicleLogWriter createWriter() throws IOException {
        return ChronicleLogWriters.text(
            config,
            super.getPath(),
            super.getDateFormat(),
            super.getStackTraceDepth()
        );
    }

    protected VanillaLogAppenderConfig getChronicleConfig() {
        return this.config;
    }

    // *************************************************************************
    //
    // *************************************************************************

    @PluginFactory
    public static TextVanillaChronicleAppender createAppender(
        @PluginAttribute("name") final String name,
        @PluginAttribute("path") final String path,
        @PluginAttribute("dateFormat") final String dateFormat,
        @PluginAttribute("stackTraceDepth") final String stackTraceDepth,
        @PluginElement("vanillaChronicleConfig") final VanillaChronicleCfg chronicleConfig,
        @PluginElement("filter") final Filter filter) {
        if(name == null) {
            LOGGER.error("No name provided for TextVanillaChronicleAppender");
            return null;
        }

        if(path == null) {
            LOGGER.error("No path provided for TextVanillaChronicleAppender");
            return null;
        }

        final TextVanillaChronicleAppender appender =
            new TextVanillaChronicleAppender(name, filter, path, chronicleConfig);

        if(dateFormat != null) {
            appender.setDateFormat(dateFormat);
        }

        if(stackTraceDepth != null) {
            appender.setStackTraceDepth(Integer.parseInt(stackTraceDepth));
        }

        return appender;
    }
}
