package org.gtd.properties.model;

import java.io.Serializable;

/**
 * Created by vm033450 on 12/18/17.
 */
public class Config implements Serializable {
    private ExtractConfig extractConfig;
    private TableConfig tableConfig;

    public Config(ExtractConfig extractConfig, TableConfig tableConfig) {
        this.extractConfig = extractConfig;
        this.tableConfig = tableConfig;
    }

    public Config() {
    }

    public ExtractConfig getExtractConfig() {
        return extractConfig;
    }

    public void setExtractConfig(ExtractConfig extractConfig) {
        this.extractConfig = extractConfig;
    }

    public TableConfig getTableConfig() {
        return tableConfig;
    }

    public void setTableConfig(TableConfig tableConfig) {
        this.tableConfig = tableConfig;
    }
}
