package code.cells.InstaRama.com.dtos;

public class CategoryRequest {

    private Long categoryId;
    private String categoryName;
    private String kingdom;
    public CategoryRequest() {
    }
    public CategoryRequest(
            Long categoryId,
            String categoryName,
            String kingdom
    ) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.kingdom = kingdom;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getKingdom() {
        return kingdom;
    }
    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }
}