package com.mateusrovari.navigationdrawer;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Planet implements Serializable {

    private String name;
    private int imageId;

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public static List<Planet> buildPlanets(Context context) {
        List<Planet> planets = new ArrayList<>();

        Resources resources = context.getResources();
        String[] names = resources.getStringArray(R.array.planets_names);
        TypedArray images = resources.obtainTypedArray(R.array.planets_imgs);

        for (int i = 0; i < names.length; i++) {
            Planet planet = new Planet();
            planet.name = names[i];
            planet.imageId = images.getResourceId(i, -1);
            planets.add(planet);
        }

        images.recycle();

        return planets;
    }
}
