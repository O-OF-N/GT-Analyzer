package org.gtd.extract;

import org.apache.crunch.MapFn;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.gtd.Attack;
import org.gtd.Location;
import org.gtd.Target;
import org.gtd.Time;
import org.gtd.util.ModelUtil;

/**
 * Created by vm033450 on 11/20/17.
 */
class ExtractFn extends MapFn<String, Put> {

    public Put map(String attackDetails) {
        String[] attack = attackDetails.split(",");
        Location location = Location.newBuilder()
                .setCity(attack[12])
                .setCountry(attack[8])
                .setRegion(attack[10])
                .setState(attack[11])
                .setLattitude(attack[13])
                .setLongitude(attack[14])
                .build();
        Time time = Time.newBuilder()
                .setYear(attack[1])
                .setMonth(attack[2])
                .setDt(attack[3]).build();
        Target target = Target.newBuilder()
                .setTarget1(attack[39])
                .setType(attack[35])
                .setSubType(attack[37])
                .setCorp(attack[38])
                .setNationality1(attack[40])
                .build();

        Attack attack1 = Attack.newBuilder().setSuicide(attack[27] == "0" ? false : true).setType(attack[29]).build();
        Put put = new Put(Bytes.toBytes(attack[0]));
        ModelUtil<Location> locationModelUtil = new ModelUtil<Location>();
        ModelUtil<Target> targetModelUtil = new ModelUtil<Target>();
        ModelUtil<Time> timeModelUtil = new ModelUtil<Time>();
        ModelUtil<Attack> attackModelUtil = new ModelUtil<Attack>();
        try {
            put.addColumn(Bytes.toBytes("Location"), Bytes.toBytes("RAW"), locationModelUtil.serialize(location));
            put.addColumn(Bytes.toBytes("Target"), Bytes.toBytes("RAW"), targetModelUtil.serialize(target));
            put.addColumn(Bytes.toBytes("Time"), Bytes.toBytes("RAW"), timeModelUtil.serialize(time));
            put.addColumn(Bytes.toBytes("Attack"), Bytes.toBytes("RAW"), attackModelUtil.serialize(attack1));
            return put;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
