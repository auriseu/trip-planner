package com.aurimas.tripplanner.service;


import com.aurimas.tripplanner.entity.Item;
import com.aurimas.tripplanner.entity.ItemSeason;
import com.aurimas.tripplanner.entity.RequirementFrequency;
import com.aurimas.tripplanner.model.ItemToTake;
import com.aurimas.tripplanner.model.TripPlan;
import com.aurimas.tripplanner.repository.ItemRepository;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private TripService service;

    @Test
    void shouldGetTripPLan(){
        when(itemRepository.findAll()).thenReturn(List.of(
                new Item(10001, "Backpack", 1, RequirementFrequency.PER_TRIP, Collections.emptyList()),
                new Item(10002, "Tent", 1, RequirementFrequency.PER_TRIP_SINCE_DAY_2, Collections.emptyList()),
                new Item(10003, "Portion of food", 3, RequirementFrequency.PER_DAY, Collections.emptyList()),
                new Item(10004, "Sunscreen", 1, RequirementFrequency.PER_TRIP, List.of(
                        new ItemSeason(20001, Month.MAY),
                        new ItemSeason(20002, Month.JUNE),
                        new ItemSeason(20003, Month.JULY),
                        new ItemSeason(20004, Month.AUGUST)
                ))
                ));

        // Act
        TripPlan tripPlan = service.getTripPlan(100, 2, Month.MAY);

        // Assert
        assertThat(tripPlan.getMonth(), equalTo("MAY"));
        assertThat(tripPlan.getDays(), equalTo(2));
        assertThat(tripPlan.getKilometers(), equalTo(100));

        List<ItemToTake> items = tripPlan.getItems();
        assertThat(items, hasSize(4));

        // Backpack
        Optional<ItemToTake> backpack = items.stream()
                .filter(item -> "Backpack".equals(item.getItemName()))
                .findFirst();
        assertThat(backpack.isPresent(), equalTo(true));
        assertThat(backpack.get().getAmount(), equalTo(1));

        // Tent
        Optional<ItemToTake> tent = items.stream()
                .filter(item -> "Tent".equals(item.getItemName()))
                .findFirst();
        assertThat(tent.isPresent(), equalTo(true));
        assertThat(tent.get().getAmount(), equalTo(1));

        // Portion of food
        Optional<ItemToTake> food = items.stream()
                .filter(item -> "Portion of food".equals(item.getItemName()))
                .findFirst();
        assertThat(food.isPresent(), equalTo(true));
        assertThat(food.get().getAmount(), equalTo(6));

        // Sunscreen
        Optional<ItemToTake> sunscreen = items.stream()
                .filter(item -> "Sunscreen".equals(item.getItemName()))
                .findFirst();
        assertThat(sunscreen.isPresent(), equalTo(true));
        assertThat(sunscreen.get().getAmount(), equalTo(1));
    }
}