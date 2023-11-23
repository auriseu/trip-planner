package com.aurimas.tripplanner.controller;

import com.aurimas.tripplanner.exception.TripException;
import com.aurimas.tripplanner.model.TripPlan;
import com.aurimas.tripplanner.service.TripService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

@RestController
@RequestMapping("/trip")
@Tag(name = "Trip controller")
public class TripController {

    @Value("${tripplanner.default.kilometers}")
    private int defaultKilometers;

    @Value("${tripplanner.default.days}")
    private int defaultDays;

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping(path = "/items")
    public TripPlan getTripPlan(
            @RequestParam(required = false) Integer kilometers,
            @RequestParam(required = false) Integer days,
            @RequestParam(required = false) Integer month) {

        Month monthValidated = Optional.ofNullable(month)
                .map(m -> {
                    if (m < 1 || m > 12) {
                        throw new TripException("Invalid value for month: " + month);
                    }

                    return Month.of(m);
                })
                .orElse(LocalDate.now().getMonth());

        int kilometersValidated = Optional.ofNullable(kilometers)
                .map(k -> {
                    if (k < 1) {
                        throw new TripException("Invalid value for kilometers: " + k);
                    }

                    return k;
                }).orElse(defaultKilometers);

        int daysValidated = Optional.ofNullable(days)
                .map(d -> {
                    if (d < 1) {
                        throw new TripException("Invalid value for days: " + d);
                    }

                    return d;
                }).orElse(defaultDays);

        return tripService.getTripPlan(kilometersValidated, daysValidated, monthValidated);
    }
}