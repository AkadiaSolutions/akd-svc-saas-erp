package br.akd.svc.akadia.models.dto.global;

import br.akd.svc.akadia.models.dto.site.ClienteSistemaDto;
import br.akd.svc.akadia.models.dto.site.EmpresaDto;
import br.akd.svc.akadia.models.enums.global.EstadoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDto {
    private Long id;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String codigoPostal;
    private String cidade;
    private EstadoEnum estadoEnum;

    //TODO HABILITAR CLIENTE NO SERVIÇO DO SISTEMA
//    private ClienteDto cliente;

    //TODO HABILITAR FORNECEDOR NO SERVIÇO DO SISTEMA
//    private FornecedorDto fornecedor;

    private ClienteSistemaDto clienteSistema;
    private EmpresaDto empresa;

}