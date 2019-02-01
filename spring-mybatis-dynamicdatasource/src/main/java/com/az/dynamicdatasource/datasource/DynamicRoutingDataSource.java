package com.az.dynamicdatasource.datasource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.text.MessageFormat;

/**
 * @author quzhengguo
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    private Log logger= LogFactory.getLog(this.getClass());

    @Override
    protected Object determineCurrentLookupKey() {
        logger.info(MessageFormat.format("Current DataSource is [{0}]",
                            DynamicDataSourceContextHolder.getDatasourceKey()));
        return DynamicDataSourceContextHolder.getDatasourceKey();
    }
}
