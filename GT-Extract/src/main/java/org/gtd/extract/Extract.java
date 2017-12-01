package org.gtd.extract;

import org.apache.crunch.PCollection;
import org.apache.crunch.Pipeline;
import org.apache.crunch.io.From;
import org.apache.crunch.io.hbase.HBaseTarget;
import org.apache.crunch.io.hbase.HBaseTypes;
import org.apache.crunch.types.PType;
import org.apache.hadoop.hbase.client.Put;

public class Extract {

    public static void extract(Pipeline p) {
        PType<Put> putPType = HBaseTypes.puts();
        String filePath = "/input";
        PCollection<String> records = p.read(From.textFile(filePath));
        PCollection<Put> puts = records.parallelDo(new ExtractFn(),putPType);
        puts.write(new HBaseTarget("GTD"));
        p.done();
    }
}
