package br.akd.svc.akadia.models.dto.bckoff;

import br.akd.svc.akadia.models.dto.global.ParentescoDto;
import br.akd.svc.akadia.models.dto.global.TelefoneDto;
import br.akd.svc.akadia.models.enums.bckoff.CargoInternoEnum;
import br.akd.svc.akadia.models.enums.bckoff.StatusAtividadeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
