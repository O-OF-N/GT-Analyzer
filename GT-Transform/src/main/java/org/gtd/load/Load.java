package org.gtd.load;

import org.apache.crunch.PCollection;
import org.apache.crunch.io.To;
import org.apache.crunch.io.hbase.HBaseTarget;
import org.apache.crunch.io.hbase.HBaseTypes;
import org.apache.hadoop.hbase.client.Put;
import org.gtd.AttacksPerCountryPerYear;

/**
 * Created by vm033450 on 11/28/17.
 */
public class Load {

    public static void load(PCollection<AttacksPerCountryPerYear> attacksPerCountryPerYearPCollection) {
        writeToHbase(attacksPerCountryPerYearPCollection);
        writeToHdfs(attacksPerCountryPerYearPCollection);
    }

    private static void writeToHbase(PCollection<AttacksPerCountryPerYear> attacksPerCountryPerYearPCollection) {
        PCollection<Put> putPCollection = attacksPerCountryPerYearPCollection.parallelDo(new LoadFn(), HBaseTypes.puts());
        putPCollection.write(new HBaseTarget("GTD-ATTACKS-PER-YEAR-PER-COUNTRY"));
        putPCollection.getPipeline().done();
    }

    private static void writeToHdfs(PCollection<AttacksPerCountryPerYear> attacksPerCountryPerYearPCollection) {
        attacksPerCountryPerYearPCollection.write(To.textFile("/output"));
        attacksPerCountryPerYearPCollection.getPipeline().done();
    }
}
