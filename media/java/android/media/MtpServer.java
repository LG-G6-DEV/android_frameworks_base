/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.media;

import android.util.Log;

/**
 * Java wrapper for MTP/PTP support as USB responder.
 * {@hide}
 */
public class MtpServer {

    private static final String TAG = "MtpServer";

    static {
        System.loadLibrary("media_jni");
    }

    public MtpServer(MtpDatabase database, String storagePath) {
        native_setup(database, storagePath);
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            native_finalize();
        } finally {
            super.finalize();
        }
    }

    public void start() {
        native_start();
    }

    public void stop() {
        native_stop();
    }

    public void sendObjectAdded(int handle) {
        native_send_object_added(handle);
    }

    public void sendObjectRemoved(int handle) {
        native_send_object_removed(handle);
    }

    // used by the JNI code
    private int mNativeContext;

    private native final void native_setup(MtpDatabase database, String storagePath);
    private native final void native_finalize();
    private native final void native_start();
    private native final void native_stop();
    private native final void native_send_object_added(int handle);
    private native final void native_send_object_removed(int handle);
}
