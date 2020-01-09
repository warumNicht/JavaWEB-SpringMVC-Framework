package residentevil.domain.models.viewModels;

public class VirusAllViewModel {
    private String id;
    private String name;
    private String magnitude;
    private String releasedOn;

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

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(String releasedOn) {
        this.releasedOn = releasedOn;
    }
}
