package com.aurimas.tripplanner.service;

import com.aurimas.tripplanner.model.ItemToTake;
import com.aurimas.tripplanner.model.TripPlan;
import com.aurimas.tripplanner.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;

import static com.aurimas.tripplanner.entity.RequirementFrequency.PER_TRIP_SINCE_DAY_2;

@Service
public class TripService {

    private final ItemRepository itemRepository;

    @Autowired
    public TripService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public TripPlan getTripPlan(int kilometers, int days, Month month) {

        List<ItemToTake> items = itemRepository.findAll().stream()
                .filter(item -> item.getItemSeasons().isEmpty()
                        || item.getItemSeasons().stream().anyMatch(i -> month.equals(i.getMonth())))
                .filter(item -> days > 1 || !PER_TRIP_SINCE_DAY_2.equals(item.getRequirementFrequency()))
                .map(item -> {

                    int amount = switch (item.getRequirementFrequency()) {
                        case PER_DAY -> item.getRequiredAmount() * days;
                        case PER_TRIP, PER_TRIP_SINCE_DAY_2 -> item.getRequiredAmount();
                    };

                    return new ItemToTake(item.getName(), amount);
                })
                .toList();

        return new TripPlan(days, kilometers, month.name(), items);
    }
}
