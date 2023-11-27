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

        assertItemFoundAndAmount(items, "Backpack", 1);
        assertItemFoundAndAmount(items, "Tent", 1);
        assertItemFoundAndAmount(items, "Portion of food", 6);
        assertItemFoundAndAmount(items, "Sunscreen", 1);
    }

    private static void assertItemFoundAndAmount(List<ItemToTake> items, String itemName, int amount) {
        Optional<ItemToTake> itemFound = items.stream()
                .filter(item -> itemName.equals(item.getItemName()))
                .findFirst();

        assertThat(itemFound.isPresent(), equalTo(true));
        assertThat(itemFound.get().getAmount(), equalTo(amount));
    }
}