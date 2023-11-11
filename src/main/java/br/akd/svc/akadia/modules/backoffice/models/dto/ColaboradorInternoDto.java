package br.akd.svc.akadia.modules.backoffice.models.dto;

import br.akd.svc.akadia.modules.global.dto.ParentescoDto;
import br.akd.svc.akadia.modules.global.dto.TelefoneDto;
import br.akd.svc.akadia.modules.backoffice.models.enums.CargoInternoEnum;
import br.akd.svc.akadia.modules.backoffice.models.enums.StatusAtividadeEnum;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorInternoDto {
    private Long id;
    private String dataCadastro;
    private String horaCadastro;
    private String nome;
    private String email;
    private String cpf;
    private Boolean acessoSistemaLiberado;
    private String dataNascimento;
    private Double remuneracao;
    private Integer tempoFerias;
    private String entradaEmpresa;
    private String saidaEmpresa;
    private CargoInternoEnum cargoEnum;
    private StatusAtividadeEnum statusAtividadeEnum;
    private TelefoneDto telefone;
    private List<ParentescoDto> parentescos = new ArrayList<>();
    private List<ChamadoDto> chamados = new ArrayList<>();
}
