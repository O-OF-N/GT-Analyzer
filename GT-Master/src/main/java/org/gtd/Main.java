package org.gtd;

import java.io.Serializable;

import org.apache.crunch.PCollection;
import org.apache.crunch.Pipeline;
import org.apache.crunch.impl.mr.MRPipeline;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.gtd.extract.Extract;
import org.gtd.hdfs.HDFSSetup;
import org.gtd.load.Load;
import org.gtd.transform.Transform;

/**
 * Created by vm033450 on 11/20/17.
 */
public class Main extends Configured implements Tool, Serializable {

    public static void main(String[] args) {
        try {
            System.out.println("Starting.....");
            int result = ToolRunner.run(new Configuration(), new Main(), args);
            System.out.println("ending.....");
            System.exit(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int run(String[] args) throws Exception {
        Configuration configuration = getConf();
        configuration.set("mapreduce.framework.name", "local");
        configuration.set("hbase.client.keyvalue.maxsize", "-1");
        HDFSSetup.setup();
        System.out.println("Extract begins");
        Extract.newInstance().extract(getPipeLine(configuration));
        System.out.println("transform begins");
        PCollection<AttacksPerCountryPerYear> attacksPerCountryPerYearPCollection = Transform.transform(getPipeLine(configuration));
        System.out.println("load begins");
        Load.load(attacksPerCountryPerYearPCollection);
        return 0;
    }

    private Pipeline getPipeLine(Configuration configuration) {
        return new MRPipeline(Main.class, configuration);
    }
}
