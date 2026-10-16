package code.cells.InstaRama.com.services;

import code.cells.InstaRama.com.models.Kingdom;
import code.cells.InstaRama.com.repository.KingdomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KingdomService {
    @Autowired
    private KingdomRepository kingdomRepository;
    public List<Kingdom> getAllKingdoms() {
        return kingdomRepository.findAll();
    }
}