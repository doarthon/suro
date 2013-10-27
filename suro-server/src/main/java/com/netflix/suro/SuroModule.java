package com.netflix.suro;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.netflix.suro.aws.PropertyAWSCredentialsProvider;
import com.netflix.suro.jackson.DefaultObjectMapper;
import com.netflix.suro.routing.RoutingMap;
import com.netflix.suro.server.StatusServer;
import com.netflix.suro.sink.SinkManager;

import java.util.Properties;

public class SuroModule extends AbstractModule {
    private final Properties properties;
    
    public SuroModule(Properties properties) {
        this.properties = properties;
    }
    
    @Override
    protected void configure() {
        bind(AWSCredentialsProvider.class)
            .annotatedWith(Names.named("credentials")).to(PropertyAWSCredentialsProvider.class);

        bind(ObjectMapper.class).to(DefaultObjectMapper.class).asEagerSingleton();
        bind(AWSCredentialsProvider.class).to(PropertyAWSCredentialsProvider.class);
        bind(SinkManager.class);
        bind(RoutingMap.class);
        bind(SuroService.class);
        bind(StatusServer.class);
    }
}
