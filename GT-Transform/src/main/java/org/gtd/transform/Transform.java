package org.gtd.transform;

import org.apache.crunch.PCollection;
import org.apache.crunch.PTable;
import org.apache.crunch.Pipeline;
import org.apache.crunch.io.hbase.FromHBase;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.gtd.AttacksPerCountryPerYear;

/**
 * Created by vm033450 on 11/24/17.
 */
public class Transform {

    public static PCollection<AttacksPerCountryPerYear> transform(Pipeline p) {
        PTable<ImmutableBytesWritable, Result> attackDetailsPCollection = p.read(FromHBase.table("GTD"));
        PCollection<AttacksPerCountryPerYear> attacksPerCountryPerYearPCollection = AttacksPerYearByCountryTransform
                .transform(attackDetailsPCollection);
        p.done();
        return attacksPerCountryPerYearPCollection;
    }
}
