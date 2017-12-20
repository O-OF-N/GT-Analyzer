package org.gtd.extract;

import java.util.function.Function;

import org.apache.crunch.MapFn;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.gtd.Attack;
import org.gtd.Location;
import org.gtd.Target;
import org.gtd.Time;
import org.gtd.properties.model.ExtractConfig;
import org.gtd.properties.model.TableConfig;
import org.gtd.util.ModelUtil;

/**
 * Created by vm033450 on 11/20/17.
 */
class ExtractFn extends MapFn<String, Put> {

    private ExtractConfig extractConfig;
    private TableConfig tableConfig;

    ExtractFn(ExtractConfig extractConfig, TableConfig tableConfig) {
        this.extractConfig = extractConfig;
        this.tableConfig = tableConfig;
    }

    private int toInt(String str){
        return Integer.parseInt(str);
    }

    public Put map(String attackDetails) {
        String[] attack = attackDetails.split(extractConfig.getSplit());
        Location location = Location.newBuilder()
                .setCity(attack[toInt(extractConfig.getCity())])
                .setCountry(attack[toInt(extractConfig.getCountry())])
                .setRegion(attack[toInt(extractConfig.getRegion())])
                .setState(attack[toInt(extractConfig.getState())])
                .setLattitude(attack[toInt(extractConfig.getLatitude())])
                .setLongitude(attack[toInt(extractConfig.getLongitude())])
                .build();
        Time time = Time.newBuilder()
                .setYear(attack[toInt(extractConfig.getYear())])
                .setMonth(attack[toInt(extractConfig.getMonth())])
                .setDt(attack[toInt(extractConfig.getDate())]).build();
        Target target = Target.newBuilder()
                .setTarget1(attack[toInt(extractConfig.getTarget1())])
                .setType(attack[toInt(extractConfig.getType())])
                .setSubType(attack[toInt(extractConfig.getSubtype())])
                .setCorp(attack[toInt(extractConfig.getCorp())])
                .setNationality1(attack[toInt(extractConfig.getNationality1())])
                .build();

        Attack attack1 = Attack.newBuilder()
                .setSuicide(attack[toInt(extractConfig.getSuicide())] == "0" ? false : true)
                .setType(attack[toInt(extractConfig.getType())])
                .build();

        Put put = new Put(Bytes.toBytes(attack[toInt(extractConfig.getId())]));
        ModelUtil<Location> locationModelUtil = new ModelUtil<Location>();
        ModelUtil<Target> targetModelUtil = new ModelUtil<Target>();
        ModelUtil<Time> timeModelUtil = new ModelUtil<Time>();
        ModelUtil<Attack> attackModelUtil = new ModelUtil<Attack>();
        try {
            put.addColumn(Bytes.toBytes(tableConfig.getLocation()),
                    Bytes.toBytes(tableConfig.getColumn()), locationModelUtil.serialize(location));
            put.addColumn(Bytes.toBytes(tableConfig.getTarget()),
                    Bytes.toBytes(tableConfig.getColumn()), targetModelUtil.serialize(target));
            put.addColumn(Bytes.toBytes(tableConfig.getTime()),
                    Bytes.toBytes(tableConfig.getColumn()), timeModelUtil.serialize(time));
            put.addColumn(Bytes.toBytes(tableConfig.getAttack()),
                    Bytes.toBytes(tableConfig.getColumn()), attackModelUtil.serialize(attack1));
            return put;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
