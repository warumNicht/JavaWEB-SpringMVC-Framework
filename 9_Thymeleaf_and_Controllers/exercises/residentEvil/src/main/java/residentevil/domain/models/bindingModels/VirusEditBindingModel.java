package residentevil.domain.models.bindingModels;

import org.springframework.format.annotation.DateTimeFormat;
import residentevil.domain.entities.enums.Creator;
import residentevil.domain.entities.enums.Magnitude;
import residentevil.domain.entities.enums.Mutation;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VirusEditBindingModel {
    private String id;
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 10)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 100)
    private String description;

    @NotEmpty
    @Size(max = 50)
    private String sideEffects;

    @NotNull
    private Creator creator;

    private boolean isDeadly;

    private boolean isCurable;

    @NotNull
    private Mutation mutation;

    @NotNull
    @Min(value = 0)
    @Max(value = 100)
    private Integer turnoverRate;

    @NotNull
    @Min(value = 1)
    @Max(value = 12)
    private Integer hoursUntilTurn;

    @NotNull
    private Magnitude magnitude;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releasedOn;
    private List<String> capitals;

    public VirusEditBindingModel() {
        this.capitals=new ArrayList<>();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public boolean isDeadly() {
        return isDeadly;
    }

    public void setDeadly(boolean deadly) {
        isDeadly = deadly;
    }

    public boolean isCurable() {
        return isCurable;
    }

    public void setCurable(boolean curable) {
        isCurable = curable;
    }

    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    public Integer getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Integer getHoursUntilTurn() {
        return hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    public Magnitude getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    public List<String> getCapitals() {
        return capitals;
    }

    public void setCapitals(List<String> capitals) {
        this.capitals = capitals;
    }
}
