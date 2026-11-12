package code.cells.InstaRama.com.config;

import code.cells.InstaRama.com.models.Kingdom;
import code.cells.InstaRama.com.repository.KingdomRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Llena el catálogo de reinos al arrancar. PostService busca el reino por
 * (kingdom, subKingdom) y lanza 404 si no existe, así que la tabla no puede quedar vacía.
 * Solo inserta las combinaciones que falten, por lo que es seguro con ddl-auto=create o update.
 */
@Component
@AllArgsConstructor
public class KingdomSeeder implements CommandLineRunner {

    // Combinaciones que manda el front (publicacion.html y gestionarPublicaciones.html)
    private static final List<String[]> REINOS = List.of(
            new String[]{"Monera", "General"},
            new String[]{"Protista", "General"},
            new String[]{"Fungi", "General"},
            new String[]{"Plantae", "General"},
            new String[]{"Animalia", "Vertebrado"},
            new String[]{"Animalia", "Invertebrado"},
            new String[]{"Animalia", "Sin subclasificación"},
            new String[]{"Animalia", "General"},
            new String[]{"Sin Clasificar", "General"}
    );

    private final KingdomRepository kingdomRepository;

    @Override
    public void run(String... args) {
        for (String[] r : REINOS) {
            if (kingdomRepository.findByKingdomAndSubKingdom(r[0], r[1]).isEmpty()) {
                kingdomRepository.save(new Kingdom(null, r[0], r[1]));
            }
        }
    }
}
