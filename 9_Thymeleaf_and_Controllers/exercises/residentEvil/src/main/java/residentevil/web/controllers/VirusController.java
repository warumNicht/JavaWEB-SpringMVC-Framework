package residentevil.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import residentevil.domain.models.bindingModels.VirusAddBindingModel;
import residentevil.domain.models.bindingModels.VirusEditBindingModel;
import residentevil.domain.models.serviceModels.CapitalServiceModel;
import residentevil.domain.models.serviceModels.VirusServiceModel;
import residentevil.domain.models.viewModels.CapitalListViewModel;
import residentevil.domain.models.viewModels.VirusAllViewModel;
import residentevil.services.CapitalService;
import residentevil.services.VirusService;
import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/viruses")
public class VirusController extends BaseController {
    private final CapitalService capitalService;
    private final VirusService virusService;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusController(CapitalService capitalService, VirusService virusService, ModelMapper modelMapper) {
        this.capitalService = capitalService;
        this.virusService = virusService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView, @ModelAttribute(name = "bindingModel") VirusAddBindingModel model) {
        modelAndView.addObject("bindingModel", model);
        modelAndView.addObject("capitals", this.findAllCapitals());
        return super.view("add-virus", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addConfirm(@Valid @ModelAttribute(name = "bindingModel") VirusAddBindingModel model,
                                   BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("bindingModel", model);
            modelAndView.addObject("capitals", this.findAllCapitals());
            return super.view("add-virus", modelAndView);
        }
        VirusServiceModel virusServiceModel = this.modelMapper.map(model, VirusServiceModel.class);
        List<CapitalServiceModel> capitalServiceModels = model.getCapitals().stream()
                .map(c -> {
                    CapitalServiceModel capital = new CapitalServiceModel();
                    capital.setId(c);
                    return capital;
                })
                .collect(Collectors.toList());
        virusServiceModel.setCapitals(capitalServiceModels);

        VirusServiceModel createdVirus = this.virusService.createVirus(virusServiceModel);
        if (createdVirus == null) {
            throw new IllegalArgumentException("Virus creation failed!");
        }
        return super.redirect("/");
    }

    @GetMapping("/show")
    public ModelAndView showAll(ModelAndView modelAndView) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-YYYY");

        List<VirusAllViewModel> virusAllViewModels = this.virusService.findAllViruses().stream()
                .map(v -> {
                    VirusAllViewModel virus = this.modelMapper.map(v, VirusAllViewModel.class);
                    virus.setMagnitude(v.getMagnitude().name().toUpperCase());
                    String formattedDate = formatter.format(v.getReleasedOn());
                    virus.setReleasedOn(formattedDate);
                    return virus;
                }).collect(Collectors.toList());

        modelAndView.addObject("allViruses", virusAllViewModels);
        return super.view("show-all", modelAndView);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        VirusServiceModel virusServiceModel = this.virusService.findById(id);
        if (virusServiceModel == null) {
            throw new IllegalArgumentException("Virus not found!");
        }
        VirusEditBindingModel toEdit = this.modelMapper.map(virusServiceModel, VirusEditBindingModel.class);
        List<String> capitalsIds = virusServiceModel.getCapitals().stream()
                .map(c -> c.getId())
                .collect(Collectors.toList());
        toEdit.setCapitals(capitalsIds);
        modelAndView.addObject("editModel", toEdit);
        modelAndView.addObject("capitals", this.findAllCapitals());
        return super.view("edit", modelAndView);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editPost(@Valid @ModelAttribute(name = "editModel") VirusEditBindingModel model,
                                 BindingResult bindingResult,@PathVariable(name = "id") String id, ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("editModel", model);
            modelAndView.addObject("capitals", this.findAllCapitals());
            return super.view("edit", modelAndView);
        }
        VirusServiceModel toMerge = this.modelMapper.map(model, VirusServiceModel.class);
        toMerge.setId(id);
        if(this.modelIsWithoutCapitals(model)){
            toMerge.setCapitals(new ArrayList<>());
        }else {
            List<CapitalServiceModel> capitalServiceModels = model.getCapitals().stream()
                    .map(c -> {
                        CapitalServiceModel capital = new CapitalServiceModel();
                        capital.setId(c);
                        return capital;
                    })
                    .collect(Collectors.toList());
            toMerge.setCapitals(capitalServiceModels);
        }
        VirusServiceModel editedVirus = this.virusService.editVirus(toMerge);
        if (editedVirus == null) {
            throw new IllegalArgumentException("Virus edition failed!");
        }
        return super.redirect("/viruses/show");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        VirusServiceModel virusServiceModel = this.virusService.findById(id);
        if (virusServiceModel == null) {
            throw new IllegalArgumentException("Virus not found!");
        }
        VirusEditBindingModel toDel = this.modelMapper.map(virusServiceModel, VirusEditBindingModel.class);
        modelAndView.addObject("bindingModel", toDel);
        modelAndView.addObject("capitals", this.findAllCapitals());

        return super.view("delete", modelAndView);
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deletePost(@PathVariable(name = "id") String id) {
        VirusServiceModel virusServiceModel = this.virusService.findById(id);
        if (virusServiceModel == null) {
            throw new IllegalArgumentException("Virus not found!");
        }
        this.virusService.deleteVirus(id);

        return super.redirect("/viruses/show");
    }

    private boolean modelIsWithoutCapitals(VirusEditBindingModel model) {
        return model.getCapitals().contains("withoutCapitals");
    }

    private List<CapitalListViewModel> findAllCapitals() {
        return this.capitalService.findAllCapitals().stream()
                .map(c -> this.modelMapper.map(c, CapitalListViewModel.class))
                .collect(Collectors.toList());
    }
}
