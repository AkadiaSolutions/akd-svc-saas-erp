package br.akd.svc.akadia.models.dto.global;

import br.akd.svc.akadia.models.dto.site.ClienteSistemaDto;
import br.akd.svc.akadia.models.dto.site.EmpresaDto;
import br.akd.svc.akadia.models.enums.global.TipoTelefoneEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDto {
    private Long id;
    private Integer prefixo;
    private Long numero;
    private TipoTelefoneEnum tioTelefoneEnum;
    private ClienteSistemaDto clienteSistema;
    private ParentescoDto parentesco;

// TODO HABILITAR FORNECEDOR NO SERVIÇO DO SISTEMA
//    private FornecedorDto fornecedor;

// TODO HABILITAR CLIENTE NO SERVIÇO DO SISTEMA
//    private ClienteDto cliente;
    private EmpresaDto empresa;
}
