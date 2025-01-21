package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.ResourceDAO;
import fr.pantheonsorbonne.dto.InitMoneyDTO;
import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ResourceInitializerService {

    @Inject
    ResourceDAO resourceDAO;

    @Transactional
    public void initializeResources(InitMoneyDTO initRequestDTO) {
        initializeResource(ResourceType.WATER, 1000.0);
        initializeResource(ResourceType.ENERGY, 1000.0);
        initializeResource(ResourceType.FERTILIZER, 0.0);
        initializeResource(ResourceType.MONEY, initRequestDTO.getMoney());
    }

    private void initializeResource(ResourceType type, double initialQuantity) {
        resourceDAO.findByType(type).orElseGet(() -> {
            Resource resource = new Resource(type, initialQuantity);
            return resourceDAO.save(resource);
        });
    }
}
