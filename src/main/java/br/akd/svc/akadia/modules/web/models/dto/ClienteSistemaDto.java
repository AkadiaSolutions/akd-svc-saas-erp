package br.akd.svc.akadia.modules.web.models.dto;

import br.akd.svc.akadia.modules.global.dto.EnderecoDto;
import br.akd.svc.akadia.modules.global.dto.TelefoneDto;
import br.akd.svc.akadia.modules.web.models.dto.empresa.EmpresaDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClienteSistemaDto {
    private Long id;
    private String codigoClienteAsaas;
    private String dataCadastro;
    private String horaCadastro;
    private String dataNascimento;
    private String email;
    private String nome;
    private String senha;
    private String cpf;
    private Double saldo;
    private PlanoDto plano;
    private TelefoneDto telefone;
    private EnderecoDto endereco;
    private CartaoDto cartao;
    private List<PagamentoSistemaDto> pagamentos = new ArrayList<>();
    private List<EmpresaDto> empresas = new ArrayList<>();
}