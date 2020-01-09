package residentevil.domain.entities;

import residentevil.domain.entities.enums.Creator;
import residentevil.domain.entities.enums.Magnitude;
import residentevil.domain.entities.enums.Mutation;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "viruses")
public class Virus extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "side_effects")
    private String sideEffects;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "creator")
    private Creator creator;

    @Column(name = "is_deadly")
    private boolean isDeadly;

    @Column(name = "is_curable")
    private boolean isCurable;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "mutation")
    private Mutation mutation;

    @Column(name = "turnover_rate")
    private Integer turnoverRate;

    @Column(name = "hours_until_turn")
    private Integer hoursUntilTurn;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "magnitude")
    private Magnitude magnitude;

    @Column(name = "released_on")
    private LocalDate releasedOn;

    @ManyToMany
    @JoinTable(name = "viruses_capitals",
    joinColumns = @JoinColumn(name = "virus_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "capital_id", referencedColumnName = "id"))
    private List<Capital> capitals;

    public Virus() {
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

    public List<Capital> getCapitals() {
        return capitals;
    }

    public void setCapitals(List<Capital> capitals) {
        this.capitals = capitals;
    }
}
