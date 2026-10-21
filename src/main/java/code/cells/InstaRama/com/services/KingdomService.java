package code.cells.InstaRama.com.services;

import code.cells.InstaRama.com.dtos.KingdomRequest;
import code.cells.InstaRama.com.models.Kingdom;
import code.cells.InstaRama.com.repository.KingdomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KingdomService {

    @Autowired
    private KingdomRepository kingdomRepository;

    public List<Kingdom> getAllKingdoms() {
        return kingdomRepository.findAll();
    }

    public Optional<Kingdom> getKingdomById(Long id) {
        return kingdomRepository.findById(id);
    }

    public Kingdom createKingdom(KingdomRequest request) {
        Kingdom kingdom = new Kingdom();
        kingdom.setKingdom(request.kingdom());
        kingdom.setSubKingdom(request.subKingdom());
        return kingdomRepository.save(kingdom);
    }

    public void deleteKingdom(Long id) {
        kingdomRepository.deleteById(id);
    }
}