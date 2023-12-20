package tekup.tp2.Metier.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tekup.tp2.AppAdmin.Models.Models;

public interface ModelsRepository extends JpaRepository<Models,Long> {
}
