package code.cells.InstaRama.com.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class KingdomRequest {
    @NotBlank(message = "Kingdom is required")
    @Size(max = 50, message = "Kingdom must not exceed 50 characters")
    private String kingdom;
    @NotBlank(message = "SubKingdom is required")
    @Size(max = 100, message = "SubKingdom must not exceed 100 characters")
    private String subKingdom;
    // Constructor vacío
    public KingdomRequest() {
    }
    // Constructor
    public KingdomRequest(String kingdom, String subKingdom) {
        this.kingdom = kingdom;
        this.subKingdom = subKingdom;
    }
    public String getKingdom() {
        return kingdom;
    }
    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }
    public String getSubKingdom() {
        return subKingdom;
    }
    public void setSubKingdom(String subKingdom) {
        this.subKingdom = subKingdom;
    }
}