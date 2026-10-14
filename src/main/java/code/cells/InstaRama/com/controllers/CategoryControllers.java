package code.cells.InstaRama.com.controllers;

import code.cells.InstaRama.com.models.Category;
import code.cells.InstaRama.com.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> listCategories() {
        return categoryService.getAllCategories();
    }
}