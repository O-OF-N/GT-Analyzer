package org.gtd.load;

import org.apache.crunch.MapFn;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.gtd.AttacksPerCountryPerYear;
import org.gtd.util.ModelUtil;

/**
 * Created by vm033450 on 11/30/17.
 */
public class LoadFn extends MapFn<AttacksPerCountryPerYear, Put> {
    public Put map(AttacksPerCountryPerYear attacksPerCountryPerYear) {
        try {
            Put put = new Put(Bytes.toBytes(attacksPerCountryPerYear.getYear().toString()));
            ModelUtil<AttacksPerCountryPerYear> attacksPerCountryPerYearModelUtil = new ModelUtil<AttacksPerCountryPerYear>();
            put.addColumn(Bytes.toBytes("AttackDetails"), Bytes.toBytes("RAW"),
                    attacksPerCountryPerYearModelUtil.serialize(attacksPerCountryPerYear));
            return put;
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
