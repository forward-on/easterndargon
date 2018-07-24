package com.ly.database;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Date 2018-07-24 19:32
 * @Author ly
 */
@Service
@DisconfFile(
        filename = "jdbc.properties"
)
public class DataSourceConf {
    private String defaultDataSource;
    private String defaultDataSourceName;

    public DataSourceConf() {
    }

    @DisconfFileItem(
            name = "defaultDataSource",
            associateField = "defaultDataSource"
    )
    public String getDefaultDataSource() {
        return this.defaultDataSource;
    }

    public void setDefaultDataSource(String defaultDataSource) {
        this.defaultDataSource = defaultDataSource;
    }

    @DisconfFileItem(
            name = "default.datasource.name",
            associateField = "defaultDataSourceName"
    )
    public String getDefaultDataSourceName() {
        return this.defaultDataSourceName;
    }

    public void setDefaultDataSourceName(String defaultDataSourceName) {
        this.defaultDataSourceName = defaultDataSourceName;
    }
}
