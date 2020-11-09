package com.frost.MergeHtmlIntoPdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ConvertibleOutputStream extends ByteArrayOutputStream {
    public InputStream toInputStream() {
        return new ByteArrayInputStream(buf, 0, count);
    }
}