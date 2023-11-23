package com.aurimas.tripplanner.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TripPlan {

    private int days;

    private int kilometers;

    private String month;

    private List<ItemToTake> items;

}
