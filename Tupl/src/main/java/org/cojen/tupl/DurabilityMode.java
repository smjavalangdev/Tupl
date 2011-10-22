/*
 *  Copyright 2011 Brian S O'Neill
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.cojen.tupl;

/**
 * Various transaction durability modes, which control the durability strength
 * of committed transactions. Strong modes offer safety, but they are also
 * relatively slow. Weak modes are faster, but transactions committed in one of
 * these modes can get lost.
 *
 * @author Brian S O'Neill
 */
public enum DurabilityMode {
    /**
     * Strongest durability mode, which ensures all modifications are flushed
     * to non-volatile storage.
     */
    SYNC,

    /**
     * Durability mode which permits the operating system to lazily flush
     * modifications to non-volatile storage. This mode is vulnerable to power
     * failures and operating system crashes. These events can cause recently
     * committed transactions to get lost. A shutdown hook durably flushes
     * modifications when the process exits cleanly.
     */
    NO_SYNC,

    /**
     * Durability mode which writes modifications to the file system when the
     * in process buffer is full. This mode is vulnerable to power failures,
     * operating system crashes, and process crashes. These events can cause
     * recently committed transactions to get lost. A shutdown hook durably
     * flushes modifications when the process exits cleanly.
     */
    NO_FLUSH,

    /**
     * Weakest durability mode, which doesn't write anything to the redo
     * log. An unlogged transaction does not become durable until a checkpoint
     * is performed. In addition to the vulnerabilities of NO_FLUSH mode,
     * NO_LOG mode can lose recently committed transactions when the process
     * exits. No shutdown is installed to perform a checkpoint.
     */
    NO_LOG;
}