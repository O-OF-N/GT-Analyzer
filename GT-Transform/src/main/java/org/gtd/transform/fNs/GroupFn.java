package org.gtd.transform.fNs;

import org.apache.crunch.MapFn;
import org.apache.crunch.Pair;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.gtd.*;
import org.gtd.util.ModelUtil;

/**
 * Created by vm033450 on 11/25/17.
 */
public class GroupFn extends MapFn<Pair<ImmutableBytesWritable, Result>, Pair<String, Pair<String, AttackDetails>>> {

    public Pair<String, Pair<String, AttackDetails>> map(Pair<ImmutableBytesWritable, Result> input) {
        Result attackDetail = input.second();

        byte[] time = attackDetail.getValue(Bytes.toBytes("Time"), Bytes.toBytes("RAW"));
        byte[] location = attackDetail.getValue(Bytes.toBytes("Location"), Bytes.toBytes("RAW"));
        byte[] target = attackDetail.getValue(Bytes.toBytes("Target"), Bytes.toBytes("RAW"));
        byte[] attack = attackDetail.getValue(Bytes.toBytes("Attack"), Bytes.toBytes("RAW"));
        ModelUtil<Time> timeModelUtil = new ModelUtil<Time>();
        ModelUtil<Location> locationModelUtil = new ModelUtil<Location>();
        ModelUtil<Attack> attackModelUtil = new ModelUtil<Attack>();
        ModelUtil<Target> targetModelUtil = new ModelUtil<Target>();
        try {
            Time t = timeModelUtil.deserialize(time, Time.class);
            Location l = locationModelUtil.deserialize(location, Location.class);
            Target tgt = targetModelUtil.deserialize(target, Target.class);
            Attack a = attackModelUtil.deserialize(attack, Attack.class);
            AttackDetails attackDetails = AttackDetails.newBuilder()
                    .setAttack(a)
                    .setLocation(l)
                    .setTarget(tgt)
                    .setTime(t)
                    .build();
            return Pair.of(t.getYear().toString(), Pair.of(l.getCountry().toString(), attackDetails));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
