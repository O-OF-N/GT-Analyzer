package org.gtd.transform;

import org.apache.crunch.PCollection;
import org.apache.crunch.PTable;
import org.apache.crunch.Pair;
import org.apache.crunch.lib.SecondarySort;
import org.apache.crunch.types.avro.Avros;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.gtd.AttackDetails;
import org.gtd.AttacksPerCountryPerYear;
import org.gtd.properties.model.TableConfig;
import org.gtd.transform.fNs.GroupFn;
import org.gtd.transform.fNs.ResultFn;

public class AttacksPerYearByCountryTransform {

    public static PCollection<AttacksPerCountryPerYear> transform(PTable<ImmutableBytesWritable, Result> input, TableConfig config) {

        PTable<String, Pair<String, AttackDetails>> grouped = input.parallelDo(new GroupFn(config),
                Avros.tableOf(Avros.strings(), Avros.pairs(Avros.strings(), Avros.records(AttackDetails.class))));
        PCollection<AttacksPerCountryPerYear> transformed =
                SecondarySort.sortAndApply(grouped, new ResultFn(), Avros
                        .records(AttacksPerCountryPerYear.class),10);
        return transformed;
    }

}
