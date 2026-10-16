package code.cells.InstaRama.com.controllers;

import code.cells.InstaRama.com.models.Kingdom;
import code.cells.InstaRama.com.services.KingdomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kingdoms")
@CrossOrigin(origins = "*")
public class KingdomController {

    @Autowired
    private KingdomService kingdomService;

    @GetMapping
    public List<Kingdom> getAllKingdoms() {
        return kingdomService.getAllKingdoms();
    }
}