package org.highj.js_monad_ffi_generator;

import org.highj.data.List;
import org.highj.data.stateful.IO;
import org.highj.data.tuple.T0;

import java.io.File;
import java.net.URI;

public class FFIGenerator {

    public static IO<T0> generateFFI(List<URI> sourceUris, File targetFolder) {
        return () -> {
            return T0.of();
        };
    }
}
