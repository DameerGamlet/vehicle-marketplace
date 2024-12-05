package com.vehicle.online.config;

import com.vehicle.online.models.Vehicle;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class Startup {

    @Transactional
    void onStart(@Observes StartupEvent event) {
        new Vehicle("Car", "Toyota", "Camry", 2020, 20_200).persist();
        new Vehicle("Truck", "Volvo", "FH16", 2019, 75_000).persist();
        new Vehicle("Motorcycle", "Harley-Davidson", "Iron 883", 2021, 12_000).persist();
    }
}
