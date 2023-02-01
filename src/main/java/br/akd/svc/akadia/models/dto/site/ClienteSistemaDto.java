package br.akd.svc.akadia.models.dto.site;

import br.akd.svc.akadia.models.dto.global.EnderecoDto;
import br.akd.svc.akadia.models.dto.global.TelefoneDto;
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
public class ClienteSistemaDto {
    private Long id;
    private String codigoClienteAsaas;
    private String dataCadastro;
    private String horaCadastro;
    private String email;
    private String nome;
    private String senha;
    private String cpf;
    private Double saldo;
    private PlanoDto plano;
    private TelefoneDto telefone;
    private EnderecoDto endereco;
    private List<PagamentoSistemaDto> pagamentos = new ArrayList<>();
    private List<CartaoDto> cartoes = new ArrayList<>();
    private List<EmpresaDto> empresas = new ArrayList<>();
}