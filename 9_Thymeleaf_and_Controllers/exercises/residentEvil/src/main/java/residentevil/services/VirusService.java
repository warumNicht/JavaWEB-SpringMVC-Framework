package residentevil.services;

import residentevil.domain.models.serviceModels.VirusServiceModel;

import java.util.List;

public interface VirusService {
    VirusServiceModel createVirus(VirusServiceModel virusServiceModel);

    List<VirusServiceModel> findAllViruses();

    VirusServiceModel findById(String id);

    VirusServiceModel editVirus(VirusServiceModel virusServiceModel);

    void deleteVirus(String id);
}
