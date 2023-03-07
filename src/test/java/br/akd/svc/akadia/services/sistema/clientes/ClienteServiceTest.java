package br.akd.svc.akadia.services.sistema.clientes;

import br.akd.svc.akadia.models.dto.sistema.clientes.mocks.ClienteDtoBuilder;
import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.mocks.ClienteEntityBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.repositories.sistema.clientes.impl.ClienteRepositoryImpl;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Service: Cliente")
class ClienteServiceTest {

    @InjectMocks
    ClienteService clienteService;

    @Mock
    ClienteRepositoryImpl clienteRepositoryImpl;

    @Test
    @DisplayName("Deve testar método de criação de novo cliente")
    void deveTestarMetodoDeCriacaoDeNovoCliente() {

        when(clienteRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteEntityBuilder.builder().comExclusao(false).build());

        ClienteEntity clienteEntity = clienteService.criaNovoCliente(ColaboradorEntityBuilder.builder().comEmpresa().build(),
                ClienteDtoBuilder.builder()
                        .comObjetoExclusaoFalse()
                        .comEndereco()
                        .comTelefone()
                        .build());

        Assertions.assertEquals("ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, " +
                "exclusaoCliente=ExclusaoClienteEntity(id=1, dataExclusao=null, horaExclusao=null, excluido=false, " +
                "responsavelExclusao=null), endereco=null, telefone=null, colaboradorResponsavel=null, empresa=null)",
                clienteEntity.toString());
    }

    @Test
    @DisplayName("Deve testar método de atualização do cliente")
    void deveTestarMetodoDeAtualizacaoDeCliente() {

        when(clienteRepositoryImpl.implementaBuscaPorId(anyLong()))
                .thenReturn(ClienteEntityBuilder.builder()
                        .comExclusao(false)
                        .comTelefone()
                        .comEndereco()
                        .build());

        when(clienteRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteEntityBuilder.builder()
                        .comExclusao(false)
                        .comTelefone()
                        .comEndereco()
                        .build());

        ClienteEntity cliente = clienteService.atualizaCliente(ColaboradorEntityBuilder.builder().build(), 1L,
                ClienteDtoBuilder.builder()
                        .comObjetoExclusaoFalse()
                        .comTelefone()
                        .comEndereco()
                        .build());

        Assertions.assertEquals("ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, " +
                        "exclusaoCliente=ExclusaoClienteEntity(id=1, dataExclusao=null, horaExclusao=null, " +
                        "excluido=false, responsavelExclusao=null), endereco=EnderecoEntity(id=1, " +
                        "logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, " +
                        "codigoPostal=02442-090, cidade=São Paulo, complemento=Casa 4, estadoEnum=SP), " +
                        "telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefoneEnum=MOVEL_WHATSAPP), " +
                        "colaboradorResponsavel=null, empresa=null)",
                cliente.toString());
    }

    @Test
    @DisplayName("Deve testar método de validação se cpfCnpj já existe com valor existente")
    void deveTestarMetodoDeValidacaoSeCpfCnpjJaExisteComValorExistente() {

        when(clienteRepositoryImpl.implementaBuscaPorCpfCnpjIdentico(any(), any()))
                .thenReturn(Optional.of(ClienteEntityBuilder.builder().build()));

        try {
            clienteService.validaSeCpfCnpjJaExiste("123.456.789-10", 1L);
            Assertions.fail();
        }
        catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("O cpf/cnpj informado já existe",
                    invalidRequestException.getMessage());
        }

    }

    @Test
    @DisplayName("Deve testar método de validação se e-mail já existe com valor existente")
    void deveTestarMetodoDeValidacaoSeEmailJaExisteComValorExistente() {
        when(clienteRepositoryImpl.implementaBuscaPorEmailIdentico(any(), any()))
                .thenReturn(Optional.of(ClienteEntityBuilder.builder().build()));

        try {
            clienteService.validaSeEmailJaExiste("email@gmail.com", 1L);
            Assertions.fail();
        }
        catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("O e-mail informado já existe",
                    invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de validação se inscrição estadual já existe com valor existente")
    void deveTestarMetodoDeValidacaoSeInscricaoEstadualJaExisteComValorExistente() {
        when(clienteRepositoryImpl.implementaBuscaPorInscricaoEstadualIdentica(any(), any()))
                .thenReturn(Optional.of(ClienteEntityBuilder.builder().build()));

        try {
            clienteService.validaSeInscricaoEstadualJaExiste("12.123.123.12", 1L);
            Assertions.fail();
        }
        catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("A inscrição estadual informada já existe",
                    invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de obtenção de id do telefone do cliente atualizável se tiver sem telefone")
    void deveTestarMetodoDeObtencaoDeIdTelefoneDoClienteAtualizavelSeTiverSemTelefone() {
        Assertions.assertNull(clienteService
                .obtemIdTelefoneDoClienteAtualizavelSeTiver(ClienteEntityBuilder.builder().build()));
    }

    @Test
    @DisplayName("Deve testar método de obtenção de id do endereço do cliente atualizável se tiver sem endereço")
    void deveTestarMetodoDeObtencaoDeIdEnderecoDoClienteAtualizavelSeTiverSemEndereco() {
        Assertions.assertNull(clienteService
                .obtemIdEnderecoDoClienteAtualizavelSeTiver(ClienteEntityBuilder.builder().build()));
    }

    @Test
    @DisplayName("Deve testar método de remoção de cliente")
    void deveTestarMetodoDeRemocaoDeCliente() {
        when(clienteRepositoryImpl.implementaBuscaPorId(anyLong()))
                .thenReturn(ClienteEntityBuilder.builder().comExclusao(false).build());
        when(clienteRepositoryImpl.implementaPersistencia(any()))
                .thenReturn(ClienteEntityBuilder.builder().comExclusao(false).build());
        Assertions.assertEquals("ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, " +
                        "exclusaoCliente=ExclusaoClienteEntity(id=1, dataExclusao=null, horaExclusao=null, " +
                        "excluido=false, responsavelExclusao=null), endereco=null, telefone=null, " +
                        "colaboradorResponsavel=null, empresa=null)",
                clienteService.removeCliente(ColaboradorEntityBuilder.builder().build(), 1L).toString());
    }

    @Test
    @DisplayName("Deve testar método de remoção de cliente com cliente já excluído")
    void deveTestarMetodoDeRemocaoDeClienteComClienteJaExcluido() {
        when(clienteRepositoryImpl.implementaBuscaPorId(anyLong()))
                .thenReturn(ClienteEntityBuilder.builder().comExclusao(true).build());
        try {
            clienteService.removeCliente(ColaboradorEntityBuilder.builder().build(), 1L);
            Assertions.fail();
        }
        catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("Não é possível remover um cliente que já foi excluído",
                    invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de obtenção de clientes pelo nome")
    void deveTestarMetodoDeObtencaoDeClientesPeloNome() {
        List<ClienteEntity> clientes = new ArrayList<>();
        clientes.add(ClienteEntityBuilder.builder().build());
        when(clienteRepositoryImpl.implementaBuscaPorNomeSemelhante(any(), anyLong()))
                .thenReturn(clientes);
        Assertions.assertEquals("[ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, exclusaoCliente=null, " +
                        "endereco=null, telefone=null, colaboradorResponsavel=null, empresa=null)]",
                clienteService.obtemClientesPeloNome(ColaboradorEntityBuilder.builder().comEmpresa().build(),
                        "Fulano").toString());
    }

    @Test
    @DisplayName("Deve testar método de obtenção de clientes pelo nome com retorno vazio")
    void deveTestarMetodoDeObtencaoDeClientesPeloNomeComRetornoVazio() {
        List<ClienteEntity> clientes = new ArrayList<>();
        when(clienteRepositoryImpl.implementaBuscaPorNomeSemelhante(any(), anyLong()))
                .thenReturn(clientes);
        try {
            clienteService.obtemClientesPeloNome(ColaboradorEntityBuilder.builder().comEmpresa().build(),
                    "Fulano");
            Assertions.fail();
        }
        catch (ObjectNotFoundException objectNotFoundException) {
            Assertions.assertEquals("Nenhum cliente foi encontrado com o nome informado",
                    objectNotFoundException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de obtenção de clientes pelo e-mail")
    void deveTestarMetodoDeObtencaoDeClientesPeloEmail() {
        List<ClienteEntity> clientes = new ArrayList<>();
        clientes.add(ClienteEntityBuilder.builder().build());
        when(clienteRepositoryImpl.implementaBuscaPorEmailSemelhante(any(), anyLong()))
                .thenReturn(clientes);
        Assertions.assertEquals("[ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, exclusaoCliente=null, " +
                        "endereco=null, telefone=null, colaboradorResponsavel=null, empresa=null)]",
                clienteService.obtemClientesPeloEmail(ColaboradorEntityBuilder.builder().comEmpresa().build(),
                        "Fulano@gmail.com").toString());
    }

    @Test
    @DisplayName("Deve testar método de obtenção de clientes pelo e-mail com retorno vazio")
    void deveTestarMetodoDeObtencaoDeClientesPeloEmailComRetornoVazio() {
        List<ClienteEntity> clientes = new ArrayList<>();
        when(clienteRepositoryImpl.implementaBuscaPorEmailSemelhante(any(), anyLong()))
                .thenReturn(clientes);
        try {
            clienteService.obtemClientesPeloEmail(ColaboradorEntityBuilder.builder().comEmpresa().build(),
                    "Fulano@gmail.com");
            Assertions.fail();
        }
        catch (ObjectNotFoundException objectNotFoundException) {
            Assertions.assertEquals("Nenhum cliente foi encontrado com o email informado",
                    objectNotFoundException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de obtenção de clientes pelo cpf/cnpj")
    void deveTestarMetodoDeObtencaoDeClientesPeloCpfCnpj() {
        List<ClienteEntity> clientes = new ArrayList<>();
        clientes.add(ClienteEntityBuilder.builder().build());
        when(clienteRepositoryImpl.implementaBuscaPorCpfCnpjSemelhante(any(), anyLong()))
                .thenReturn(clientes);
        Assertions.assertEquals("[ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, exclusaoCliente=null, " +
                        "endereco=null, telefone=null, colaboradorResponsavel=null, empresa=null)]",
                clienteService.obtemClientesPeloCpfCnpj(ColaboradorEntityBuilder.builder().comEmpresa().build(),
                        "123.456.789-10").toString());
    }

    @Test
    @DisplayName("Deve testar método de obtenção de clientes pelo cpf/cnpj com retorno vazio")
    void deveTestarMetodoDeObtencaoDeClientesPeloCpfCnpjComRetornoVazio() {
        List<ClienteEntity> clientes = new ArrayList<>();
        when(clienteRepositoryImpl.implementaBuscaPorCpfCnpjSemelhante(any(), anyLong()))
                .thenReturn(clientes);
        try {
            clienteService.obtemClientesPeloCpfCnpj(ColaboradorEntityBuilder.builder().comEmpresa().build(),
                    "123.456.789-10");
            Assertions.fail();
        }
        catch (ObjectNotFoundException objectNotFoundException) {
            Assertions.assertEquals("Nenhum cliente foi encontrado com o cpf ou cnpj informado",
                    objectNotFoundException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de obtenção de clientes pela inscrição estadual")
    void deveTestarMetodoDeObtencaoDeClientesPelaInscricaoEstadual() {
        List<ClienteEntity> clientes = new ArrayList<>();
        clientes.add(ClienteEntityBuilder.builder().build());
        when(clienteRepositoryImpl.implementaBuscaPorInscricaoEstadualSemelhante(any(), anyLong()))
                .thenReturn(clientes);
        Assertions.assertEquals("[ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, exclusaoCliente=null, " +
                        "endereco=null, telefone=null, colaboradorResponsavel=null, empresa=null)]",
                clienteService.obtemClientesPelaInscricaoEstadual(ColaboradorEntityBuilder.builder().comEmpresa().build(),
                        "123.456.789-10").toString());
    }

    @Test
    @DisplayName("Deve testar método de obtenção de clientes pela inscrição estadual com retorno vazio")
    void deveTestarMetodoDeObtencaoDeClientesPelaInscricaoEstadualComRetornoVazio() {
        List<ClienteEntity> clientes = new ArrayList<>();
        when(clienteRepositoryImpl.implementaBuscaPorInscricaoEstadualSemelhante(any(), anyLong()))
                .thenReturn(clientes);
        try {
            clienteService.obtemClientesPelaInscricaoEstadual(ColaboradorEntityBuilder.builder().comEmpresa().build(),
                    "123.456.789-10");
            Assertions.fail();
        }
        catch (ObjectNotFoundException objectNotFoundException) {
            Assertions.assertEquals("Nenhum cliente foi encontrado com a inscrição estadual informada",
                    objectNotFoundException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de obtenção de clientes pelo telefone")
    void deveTestarMetodoDeObtencaoDeClientesPeloTelefone() {
        List<ClienteEntity> clientes = new ArrayList<>();
        clientes.add(ClienteEntityBuilder.builder().build());
        when(clienteRepositoryImpl.implementaBuscaPorTelefoneSemelhante(any(), anyLong()))
                .thenReturn(clientes);
        Assertions.assertEquals("[ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, exclusaoCliente=null, " +
                        "endereco=null, telefone=null, colaboradorResponsavel=null, empresa=null)]",
                clienteService.obtemClientesPeloTelefone(ColaboradorEntityBuilder.builder().comEmpresa().build(),
                        "40028922").toString());
    }

    @Test
    @DisplayName("Deve testar método de obtenção de clientes pelo telefone com retorno vazio")
    void deveTestarMetodoDeObtencaoDeClientesPeloTelefoneComRetornoVazio() {
        List<ClienteEntity> clientes = new ArrayList<>();
        when(clienteRepositoryImpl.implementaBuscaPorTelefoneSemelhante(any(), anyLong()))
                .thenReturn(clientes);
        try {
            clienteService.obtemClientesPeloTelefone(ColaboradorEntityBuilder.builder().comEmpresa().build(),
                    "40028922");
            Assertions.fail();
        }
        catch (ObjectNotFoundException objectNotFoundException) {
            Assertions.assertEquals("Nenhum cliente foi encontrado com o telefone informado",
                    objectNotFoundException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de obtenção de clientes por range de data")
    void deveTestarMetodoDeObtencaoDeClientesPorRangeDeData() {
        List<ClienteEntity> clientes = new ArrayList<>();
        clientes.add(ClienteEntityBuilder.builder().build());
        when(clienteRepositoryImpl.implementaBuscaPorPeriodo(any(), any(), anyLong()))
                .thenReturn(clientes);
        Assertions.assertEquals("[ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, exclusaoCliente=null, " +
                        "endereco=null, telefone=null, colaboradorResponsavel=null, empresa=null)]",
                clienteService.obtemClientesPorRangeDeData(ColaboradorEntityBuilder.builder().comEmpresa().build(),
                        "2023-03-07", "2023-03-08").toString());
    }

    @Test
    @DisplayName("Deve testar método de obtenção de clientes por range de data com retorno vazio")
    void deveTestarMetodoDeObtencaoDeClientesPorRangeDeDataComRetornoVazio() {
        List<ClienteEntity> clientes = new ArrayList<>();
        when(clienteRepositoryImpl.implementaBuscaPorPeriodo(any(), any(), anyLong()))
                .thenReturn(clientes);
        try {
            clienteService.obtemClientesPorRangeDeData(ColaboradorEntityBuilder.builder().comEmpresa().build(),
                    "2023-03-07", "2023-03-08");
            Assertions.fail();
        }
        catch (ObjectNotFoundException objectNotFoundException) {
            Assertions.assertEquals("Nenhum cliente foi encontrado na data indicada",
                    objectNotFoundException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de validação de datas com data inicial inválida")
    void deveTestarMetodoDeValidacaoDeDatasComDataInicialInvalida() {
        try {
            clienteService.realizaValidacaoDasDatasRecebidas("2023-03", "2023-03-08");
            Assertions.fail();
        }
        catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("A data de início não possui um padrão válido",
                    invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de validação de datas com data final inválida")
    void deveTestarMetodoDeValidacaoDeDatasComDataFinalInvalida() {
        try {
            clienteService.realizaValidacaoDasDatasRecebidas("2023-03-07", "2023-03");
            Assertions.fail();
        }
        catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("A data final não possui um padrão válido",
                    invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de validação de datas com data inicial posterior à data final")
    void deveTestarMetodoDeValidacaoDeDatasComDataInicialPosteriorADataFinal() {
        try {
            clienteService.realizaValidacaoDasDatasRecebidas("2023-03-08", "2023-03-07");
            Assertions.fail();
        }
        catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("A data de início deve ser anterior ou igual à data final",
                    invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de obtenção de clientes por mes")
    void deveTestarMetodoDeObtencaoDeClientesPorMes() {
        List<ClienteEntity> clientes = new ArrayList<>();
        clientes.add(ClienteEntityBuilder.builder().build());
        when(clienteRepositoryImpl.implementaBuscaPorPeriodo(any(), any(), anyLong()))
                .thenReturn(clientes);
        Assertions.assertEquals("[ClienteEntity(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, exclusaoCliente=null, " +
                        "endereco=null, telefone=null, colaboradorResponsavel=null, empresa=null)]",
                clienteService.obtemClientesPorMes(ColaboradorEntityBuilder.builder().comEmpresa().build(),
                        "3", "2023").toString());
    }

    @Test
    @DisplayName("Deve testar método de obtenção de clientes por mes com retorno vazio")
    void deveTestarMetodoDeObtencaoDeClientesPorMesComRetornoVazio() {
        List<ClienteEntity> clientes = new ArrayList<>();
        when(clienteRepositoryImpl.implementaBuscaPorPeriodo(any(), any(), anyLong()))
                .thenReturn(clientes);
        try {
            clienteService.obtemClientesPorMes(ColaboradorEntityBuilder.builder().comEmpresa().build(),
                    "03", "2023");
            Assertions.fail();
        }
        catch (ObjectNotFoundException objectNotFoundException) {
            Assertions.assertEquals("Nenhum cliente foi encontrado no mês e ano indicados",
                    objectNotFoundException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de validação de mes e ano com mês inválido")
    void deveTestarMetodoDeValidacaoDeMesAnoComMesInvalido() {
        try {
            clienteService.realizaValidacaoDoMesAno("mes", "2023");
            Assertions.fail();
        }
        catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("O mês não possui um padrão válido",
                    invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de validação de mes e ano com ano inválido")
    void deveTestarMetodoDeValidacaoDeMesAnoComAnoInvalido() {
        try {
            clienteService.realizaValidacaoDoMesAno("03", "");
            Assertions.fail();
        }
        catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("O ano não possui um padrão válido",
                    invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de validação de mes e ano com mês inexistente")
    void deveTestarMetodoDeValidacaoDeMesAnoComMesInexistente() {
        try {
            clienteService.realizaValidacaoDoMesAno("14", "2023");
            Assertions.fail();
        }
        catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("O mês deve possuir um valor entre 1 e 12",
                    invalidRequestException.getMessage());
        }
    }

    @Test
    @DisplayName("Deve testar método de validação de mes e ano com ano não permitido")
    void deveTestarMetodoDeValidacaoDeMesAnoComAnoNaoPermitido() {
        try {
            clienteService.realizaValidacaoDoMesAno("6", "2500");
            Assertions.fail();
        }
        catch (InvalidRequestException invalidRequestException) {
            Assertions.assertEquals("O ano deve possuir um valor entre 2020 e 2099",
                    invalidRequestException.getMessage());
        }
    }

}
