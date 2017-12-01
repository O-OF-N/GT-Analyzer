package org.gtd;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;

/**
 * Created by vm033450 on 11/21/17.
 */
public class Setup {

    static void setup() {
        try {
            final String sourceTable = "GTD";
            final String resultTable = "GTD-ATTACKS-PER-YEAR-PER-COUNTRY";
            Configuration conf = HBaseConfiguration.create();
            Connection connection = ConnectionFactory.createConnection(conf);
            HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();
            ifExistsDelete(admin, sourceTable);
            ifExistsDelete(admin, resultTable);
            HTableDescriptor gtdDescriptorSource = createTableDescriptor(sourceTable, "Location", "Time", "Attack", "Target");
            admin.createTable(gtdDescriptorSource);
            HTableDescriptor gtdDescriptorResult = createTableDescriptor(resultTable, "AttackDetails");
            admin.createTable(gtdDescriptorResult);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static HTableDescriptor createTableDescriptor(String tableName, String... familyNames) {
        HTableDescriptor descriptor = new HTableDescriptor(TableName.valueOf(tableName));
        for (String familyName : familyNames)
            descriptor.addFamily(new HColumnDescriptor(familyName));
        return descriptor;
    }

    private static void ifExistsDelete(HBaseAdmin admin, String tableName) throws Exception {
        if (admin.tableExists(tableName)) {
            admin.disableTable(tableName);
            admin.deleteTables(tableName);
        }
    }
}
