package org.gtd.util;

import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;
import org.apache.commons.io.output.ByteArrayOutputStream;

/**
 * Created by vm033450 on 11/25/17.
 */
public class ModelUtil<T> {

    public T deserialize(byte[] bytes, Class c) throws IOException {
        DatumReader<T> reader = new SpecificDatumReader<T>(c);
        BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(bytes, null);
        return reader.read(null, decoder);
    }

    public byte[] serialize(T record) throws IOException {
        if (record == null)
            throw new IllegalArgumentException("object cannot be null");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BinaryEncoder encoder = EncoderFactory.get().directBinaryEncoder(byteArrayOutputStream, null);
        Schema schema = ((SpecificRecord) record).getSchema();
        DatumWriter<T> writer = new SpecificDatumWriter<T>(schema);
        try {
            writer.write(record, encoder);
            encoder.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to serialize avro object", e);
        } finally {
            byteArrayOutputStream.close();
        }
    }
}
