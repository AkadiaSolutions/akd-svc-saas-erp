package br.akd.svc.akadia.models.dto.sistema.clientes.responses;

import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetaDadosCliente {
    Integer totalClientesCadastrados;
    ClienteEntity clienteComMaiorGiro;
    ClienteEntity clienteComMaisOrdens;
    String bairroComMaisClientes;
}
