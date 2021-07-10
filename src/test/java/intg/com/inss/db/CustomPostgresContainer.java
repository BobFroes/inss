package com.inss.db;

import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Objects;

public class CustomPostgresContainer extends PostgreSQLContainer<CustomPostgresContainer> {

    private static final String IMAGE_VERSION = "postgres:11.2";

    private static CustomPostgresContainer container;

    private CustomPostgresContainer() {
        super(IMAGE_VERSION);
    }

    public static CustomPostgresContainer getInstance() {

        if (Objects.isNull(container)) {
            container = new CustomPostgresContainer();
        }

        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }
}
