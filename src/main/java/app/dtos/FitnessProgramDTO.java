package app.dtos;

import lombok.Data;

@Data
public class FitnessProgramDTO {
    private String name;
    private String price;
    private String duration;
    private String description;
    private int level;
    private String location;
    private String selectedCategory;

    public FitnessProgramDTO() {
    }

}
