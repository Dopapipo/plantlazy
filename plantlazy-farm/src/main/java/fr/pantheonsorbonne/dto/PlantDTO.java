package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.plant.stat.PlantGrowthLevel;
import fr.pantheonsorbonne.entity.plant.stat.SoilStat;
import fr.pantheonsorbonne.entity.plant.stat.SunStat;
import fr.pantheonsorbonne.entity.plant.stat.WaterStat;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public record PlantDTO(PlantType type,
                       WaterStat water,
                       SunStat sun,
                       SoilStat soil,
                       boolean isDead,
                       Long timeOfDeath,
                       String causeOfDeath,
                       PlantGrowthLevel growthLevel,
                       boolean sold,
                       UUID id,
                       boolean composted) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public PlantDTO(PlantEntity plant) {
        this(plant.getType(),
                plant.getWater(),
                plant.getSun(),
                plant.getSoil(),
                plant.isDead(),
                plant.getTimeOfDeath(),
                plant.getCauseOfDeath(),
                plant.getGrowthLevel(),
                plant.isSold(),
                plant.getId(),
                plant.getComposted());
    }

    public UUID getId() {
        return id;
    }
}
