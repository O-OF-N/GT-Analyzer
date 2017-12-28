package org.gtd.extract;

import org.apache.crunch.PCollection;
import org.apache.crunch.Pipeline;
import org.apache.crunch.io.From;
import org.apache.crunch.io.hbase.HBaseTarget;
import org.apache.crunch.io.hbase.HBaseTypes;
import org.apache.crunch.types.PType;
import org.apache.hadoop.hbase.client.Put;
import org.gtd.properties.ConfigUtil;
import org.gtd.properties.model.Config;
import org.gtd.properties.model.ExtractConfig;
import org.gtd.properties.model.TableConfig;

public class Extract {

    private static final String SOURCE_PATH = "/input";

    public static Extract newInstance() {
        return new Extract();
    }

    public void extract(Pipeline p) throws Exception {
        if (p == null)
            throw new Exception("Empty pipeline. Extract failed");
        try {
            PType<Put> putPType = HBaseTypes.puts();
            Config config = ConfigUtil.getConfig();
            ExtractConfig extractConfig = config.getExtractConfig();
            TableConfig tableConfig = config.getTableConfig();
            if (SOURCE_PATH == null || SOURCE_PATH.length() < 1)
                throw new Exception("Incorrect file path. Extract failed");
            PCollection<String> records = p.read(From.textFile(SOURCE_PATH));
            if (records == null || records.getSize() < 1)
                throw new Exception("The input file may be empty. Extract failed");

            PCollection<Put> puts = records.parallelDo(getExtractFn(extractConfig, tableConfig), putPType);
            if (puts == null || puts.getSize() < 1)
                throw new Exception("Writing to the input Hbase failed");
            puts.write(new HBaseTarget(tableConfig.getTable()));
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            p.done();
        }
    }

    ExtractFn getExtractFn(ExtractConfig extractConfig, TableConfig tableConfig) {
        return new ExtractFn(extractConfig, tableConfig);
    }

}
