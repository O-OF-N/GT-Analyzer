package org.gtd.transform;

import org.apache.crunch.PCollection;
import org.apache.crunch.PTable;
import org.apache.crunch.Pipeline;
import org.apache.crunch.io.hbase.FromHBase;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.gtd.AttacksPerCountryPerYear;
import org.gtd.properties.ConfigUtil;
import org.gtd.properties.model.TableConfig;

/**
 * Created by vm033450 on 11/24/17.
 */
public class Transform {

    public static PCollection<AttacksPerCountryPerYear> transform(Pipeline p) throws Exception {
        try {
            TableConfig config = ConfigUtil.getConfig().getTableConfig();

            PTable<ImmutableBytesWritable, Result> attackDetailsPCollection = p.read(FromHBase.table(config.getTable()));
            PCollection<AttacksPerCountryPerYear> attacksPerCountryPerYearPCollection = AttacksPerYearByCountryTransform
                    .transform(attackDetailsPCollection,config);
            p.done();
            return attacksPerCountryPerYearPCollection;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
