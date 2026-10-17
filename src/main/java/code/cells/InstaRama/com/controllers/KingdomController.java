package code.cells.InstaRama.com.controllers;
import code.cells.InstaRama.com.dtos.KingdomRequest;
import code.cells.InstaRama.com.models.Kingdom;
import code.cells.InstaRama.com.services.KingdomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kingdoms")
@CrossOrigin(origins = "*")
public class KingdomController {

    @Autowired
    private
    KingdomService kingdomService;

    @GetMapping
    public ResponseEntity<List<Kingdom>> getAllKingdoms() {
        List<Kingdom> kingdoms = kingdomService.getAllKingdoms();
        return ResponseEntity.ok(kingdoms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kingdom> getKingdomById(@PathVariable Long id) {
        return kingdomService.getKingdomById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Kingdom> createKingdom(@Valid @RequestBody KingdomRequest request) {
        Kingdom newKingdom = kingdomService.createKingdom(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newKingdom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKingdom(@PathVariable Long id) {
        if (kingdomService.getKingdomById(id).isPresent()) {
            kingdomService.deleteKingdom(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}