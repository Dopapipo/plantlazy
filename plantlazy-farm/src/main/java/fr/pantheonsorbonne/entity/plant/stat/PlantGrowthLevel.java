package fr.pantheonsorbonne.entity.plant.stat;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PlantGrowthLevel implements Serializable {
    int value;
    int MAX_VALUE = 48;


    public PlantGrowthLevel(int value) {
        this.value = value;
    }

    public boolean isMature() {
        return this.getGrowthPercentage()>0.7;
    }

    private double getGrowthPercentage() {
        return (double) value / MAX_VALUE;
    }
    public PlantGrowthLevel() {

    }

    public void grow() {
        if (value < MAX_VALUE) {
            value++;
        }
    }
}
