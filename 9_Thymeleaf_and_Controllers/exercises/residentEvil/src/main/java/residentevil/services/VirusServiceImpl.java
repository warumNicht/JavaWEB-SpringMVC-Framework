package residentevil.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import residentevil.domain.entities.Virus;
import residentevil.domain.models.serviceModels.VirusServiceModel;
import residentevil.domain.models.viewModels.VirusAllViewModel;
import residentevil.repository.VirusRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl implements VirusService {
    private final VirusRepository virusRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository, ModelMapper modelMapper) {
        this.virusRepository = virusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VirusServiceModel createVirus(VirusServiceModel virusServiceModel) {
        try {
            Virus virusToSave = this.modelMapper.map(virusServiceModel, Virus.class);
            Virus virus = this.virusRepository.saveAndFlush(virusToSave);
            return this.modelMapper.map(virus, VirusServiceModel.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<VirusServiceModel> findAllViruses() {
        return this.virusRepository.findAll().stream()
                .map(v -> this.modelMapper.map(v, VirusServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public VirusServiceModel findById(String id) {
        Virus virus = this.virusRepository.findById(id).orElse(null);
        if(virus==null){
            return null;
        }
        return this.modelMapper.map(virus,VirusServiceModel.class);
    }

    @Override
    public VirusServiceModel editVirus(VirusServiceModel virusServiceModel) {
        Virus virus = this.modelMapper.map(virusServiceModel, Virus.class);
        Virus editedVirus = this.virusRepository.save(virus);
        return this.modelMapper.map(editedVirus,VirusServiceModel.class);
    }

    @Override
    public void deleteVirus(String id) {
        this.virusRepository.deleteById(id);
    }
}
