package fr.pantheonsorbonne.entity.seed;

import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;
import fr.pantheonsorbonne.entity.plant.stat.FullPlantStats;
import fr.pantheonsorbonne.entity.plant.stat.SoilStat;
import fr.pantheonsorbonne.entity.plant.stat.SunStat;
import fr.pantheonsorbonne.entity.plant.stat.WaterStat;

public class HighQualitySeed extends GenericSeed {
    public HighQualitySeed(PlantType type) {
        super(type, SeedQuality.HIGH);
    }

    @Override
    public FullPlantStats getInitialStats() {
        return new FullPlantStats(new WaterStat(90), new SoilStat(90), new SunStat(90));
    }
}