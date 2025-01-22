package fr.pantheonsorbonne.entity.plant.stat;

import jakarta.persistence.Embeddable;

@Embeddable
public class SoilStat extends GenericPlantStat {
    public SoilStat(int value) {
        super(value, 70, 2, StatType.SOIL);
    }

    public SoilStat() {
        super();
    }
}
