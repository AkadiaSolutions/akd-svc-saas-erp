package br.akd.svc.akadia.repositories.sistema.clientes;

import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {}
