package tekup.tp2.Metier.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tekup.tp2.AppAdmin.Models.Client;

import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Long>  {


}
