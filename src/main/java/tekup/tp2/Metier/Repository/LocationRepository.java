package tekup.tp2.Metier.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tekup.tp2.AppAdmin.Models.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
