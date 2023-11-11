package br.akd.svc.akadia.services.sistema.clientes;

import br.akd.svc.akadia.modules.erp.clientes.models.dto.response.page.ClientePageResponse;
import br.akd.svc.akadia.modules.erp.clientes.models.dto.response.ClienteResponse;
import br.akd.svc.akadia.modules.erp.clientes.models.entity.ClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.clientes.mocks.ClienteEntityBuilder;
import br.akd.svc.akadia.modules.erp.clientes.services.ClienteTypeConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("TypeConverter: Cliente")
class ClienteTypeConverterTest {

    @InjectMocks
    ClienteTypeConverter clienteTypeConverter;

    @Test
    @DisplayName("Deve testar método de conversão em massa de cliente Entity para cliente Response")
    void deveTestarMetodoDeConversaoEmMassaDeClienteEntityParaClienteResponse() {
        List<ClienteEntity> clientes = new ArrayList<>();
        clientes.add(
                ClienteEntityBuilder.builder()
                        .comColaborador()
                        .comExclusao(true)
                        .comEndereco()
                        .comTelefone()
                        .build());
        clientes.add(
                ClienteEntityBuilder.builder()
                        .comColaborador()
                        .build());

        Pageable pageable = PageRequest.of(0, 10);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), clientes.size());
        Page<ClienteEntity> clientesPaged =
                new PageImpl<>(clientes.subList(start, end), pageable, clientes.size());

        ClientePageResponse clientePageResponse =
                clienteTypeConverter
                        .converteListaDeClientesEntityParaClientesResponse(clientesPaged);

        Assertions.assertEquals("ClientePageResponse(content=[ClienteResponse(id=1, dataCadastro=2023-02-27, " +
                        "horaCadastro=17:40, dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, statusCliente=null, " +
                        "tipoPessoa=null, qtdOrdensRealizadas=null, giroTotal=null, " +
                        "exclusaoEntity=ExclusaoEntity(id=null, dataExclusao=2023-03-06, horaExclusao=14:29, " +
                        "responsavelExclusao=null), " +
                        "endereco=EnderecoEntity(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, " +
                        "bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, complemento=Casa 4, " +
                        "estado=SP), telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, " +
                        "tipoTelefone=MOVEL_WHATSAPP), nomeColaboradorResponsavel=João da Silva), " +
                        "ClienteResponse(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, dataNascimento=1998-07-21, " +
                        "nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, inscricaoEstadual=145574080114, " +
                        "email=gabrielafonso@mail.com.br, statusCliente=null, tipoPessoa=null, " +
                        "qtdOrdensRealizadas=null, giroTotal=null, exclusaoEntity=null, endereco=null, telefone=null, " +
                        "nomeColaboradorResponsavel=João da Silva)], empty=false, first=true, last=true, number=0, " +
                        "numberOfElements=2, pageNumber=0, pageSize=10, paged=true, unpaged=false, size=10, " +
                        "totalElements=2, totalPages=1)",
                clientePageResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de conversão de cliente Entity para cliente Response")
    void deveTestarMetodoDeConversaoDeClienteEntityParaClienteResponse() {
        ClienteEntity clienteEntity = ClienteEntityBuilder.builder()
                .comColaborador()
                .comExclusao(true)
                .comEndereco()
                .comTelefone()
                .build();

        ClienteResponse clienteResponse = clienteTypeConverter
                .converteClienteEntityParaClienteResponse(clienteEntity);

        Assertions.assertEquals("ClienteResponse(id=1, dataCadastro=2023-02-27, horaCadastro=17:40, " +
                        "dataNascimento=1998-07-21, nome=Gabriel Lagrota, cpfCnpj=582.645.389-32, " +
                        "inscricaoEstadual=145574080114, email=gabrielafonso@mail.com.br, statusCliente=null, " +
                        "tipoPessoa=null, qtdOrdensRealizadas=null, giroTotal=null, " +
                        "exclusaoEntity=ExclusaoEntity(id=null, dataExclusao=2023-03-06, horaExclusao=14:29, " +
                        "responsavelExclusao=null), endereco=EnderecoEntity(id=1, " +
                        "logradouro=Avenida Coronel Manuel Py, numero=583, bairro=Lauzane Paulista, " +
                        "codigoPostal=02442-090, cidade=São Paulo, complemento=Casa 4, estado=SP), " +
                        "telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefone=MOVEL_WHATSAPP), " +
                        "nomeColaboradorResponsavel=João da Silva)",
                clienteResponse.toString());
    }


}
