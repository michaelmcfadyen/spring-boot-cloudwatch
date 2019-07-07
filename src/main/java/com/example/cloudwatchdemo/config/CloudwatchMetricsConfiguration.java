package com.example.cloudwatchdemo.config;

import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsync;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsyncClient;
import com.example.cloudwatchdemo.properties.CloudWatchProperties;
import io.micrometer.cloudwatch.CloudWatchConfig;
import io.micrometer.cloudwatch.CloudWatchMeterRegistry;
import io.micrometer.core.instrument.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CloudwatchMetricsConfiguration {

    @Bean
    public CloudWatchMeterRegistry cloudWatchMeterRegistry(CloudWatchConfig config,
                                                           Clock clock, AmazonCloudWatchAsync client) {
        return new CloudWatchMeterRegistry(config, clock, client);
    }

    @Bean
    public Clock micrometerClock() {
        return Clock.SYSTEM;
    }

    @Bean
    public AmazonCloudWatchAsync amazonCloudWatchAsync() {
        return AmazonCloudWatchAsyncClient.asyncBuilder().build();
    }

    @Bean
    public CloudWatchConfig cloudWatchConfig(CloudWatchProperties properties) {
        return new CloudWatchConfig() {
            @Override
            public String prefix() {
                return null;
            }

            @Override
            public String namespace() {
                return properties.getNamespace();
            }

            @Override
            public Duration step() {
                return properties.getStep();
            }

            @Override
            public boolean enabled() {
                return properties.isEnabled();
            }

            @Override
            public int numThreads() {
                return properties.getNumThreads();
            }

            @Override
            public int batchSize() {
                return properties.getBatchSize();
            }

            @Override
            public String get(String s) {
                return null;
            }
        };
    }
}
