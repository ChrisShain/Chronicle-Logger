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

package net.openhft.chronicle.logger.slf4j.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class Slf4jBenchmark {

    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1000)
    @Measurement(iterations = 1000)
    public void measureX() {
    }

    // *************************************************************************
    //
    // *************************************************************************

    public static void main(String... args) throws Exception {
        Options opt = new OptionsBuilder()
            .include(".*" + Slf4jBenchmark.class.getSimpleName() + ".*")
            .forks(1)
            .verbosity(VerboseMode.EXTRA)
            .build();

        new Runner(opt).run();
    }
}