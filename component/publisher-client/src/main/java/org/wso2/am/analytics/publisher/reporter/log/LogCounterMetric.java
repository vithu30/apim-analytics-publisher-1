/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.am.analytics.publisher.reporter.log;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.am.analytics.publisher.exception.MetricReportingException;
import org.wso2.am.analytics.publisher.reporter.CounterMetric;
import org.wso2.am.analytics.publisher.reporter.MetricEventBuilder;
import org.wso2.am.analytics.publisher.reporter.MetricSchema;

import java.util.Map;

/**
 * Log Counter Metrics class, This class can be used to log analytics event to a separate log file.
 */
public class LogCounterMetric implements CounterMetric {
    private static final Logger log = LoggerFactory.getLogger(LogCounterMetric.class);
    private final String name;
    private final Gson gson;

    protected LogCounterMetric(String name) {
        this.name = name;
        this.gson = new Gson();
    }

    @Override
    public int incrementCount(MetricEventBuilder builder) throws MetricReportingException {
        Map<String, Object> event = builder.build();
        String jsonString = gson.toJson(event);
        log.info("apimMetrics: " + name.replaceAll("[\r\n]", "") + ", properties :" +
                jsonString.replaceAll("[\r\n]", ""));
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public MetricSchema getSchema() {
        return null;
    }

    @Override
    public MetricEventBuilder getEventBuilder() {
        return new LogMetricEventBuilder();
    }
}
