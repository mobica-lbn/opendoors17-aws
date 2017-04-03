package com.mobica.cloud.aws.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {
    public static final Regions REGION = Regions.US_EAST_1;

    @Bean
    public AWSCredentialsProvider credentialsProvider() {
        return new EnvironmentVariableCredentialsProvider();
    }

}
