package br.akd.svc.akadia.models.dto.site;

import br.akd.svc.akadia.models.dto.global.EnderecoDto;
import br.akd.svc.akadia.models.dto.global.TelefoneDto;
import br.akd.svc.akadia.models.dto.site.empresa.EmpresaDto;
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