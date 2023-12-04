package tekup.tp2.Metier.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tekup.tp2.AppAdmin.Models.Client;


public interface ClientRepository extends JpaRepository<Client, Long>  {
}
