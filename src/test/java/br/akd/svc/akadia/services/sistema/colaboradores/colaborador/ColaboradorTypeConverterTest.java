package br.akd.svc.akadia.services.sistema.colaboradores.colaborador;

import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.dto.response.page.ColaboradorPageResponse;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.dto.response.ColaboradorResponse;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.services.ColaboradorTypeConverter;
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
@DisplayName("TypeConverter: Colaborador")
class ColaboradorTypeConverterTest {

    @InjectMocks
    ColaboradorTypeConverter colaboradorTypeConverter;

    @Test
    @DisplayName("Deve testar método de conversão em massa de colaborador Entity para colaborador Response")
    void deveTestarMetodoDeConversaoEmMassaDeColaboradorEntityParaColaboradorResponse() {
        List<ColaboradorEntity> colaboradores = new ArrayList<>();
        colaboradores.add(
                ColaboradorEntityBuilder.builder()
                        .comAcessoCompleto()
                        .comEmpresa()
                        .comTelefone()
                        .comEndereco()
                        .comExclusao()
                        .build());
        colaboradores.add(
                ColaboradorEntityBuilder.builder()
                        .comAcessoCompleto()
                        .comExclusao()
                        .build());

        Pageable pageable = PageRequest.of(0, 10);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), colaboradores.size());
        Page<ColaboradorEntity> colaboradoresPaged =
                new PageImpl<>(colaboradores.subList(start, end), pageable, colaboradores.size());

        ColaboradorPageResponse colaboradorPageResponse =
                colaboradorTypeConverter
                        .converteListaDeColaboradoresEntityParaColaboradoresResponse(colaboradoresPaged);

        Assertions.assertEquals("ColaboradorPageResponse(content=[ColaboradorResponse(id=1, " +
                "dataCadastro=2023-02-13, horaCadastro=10:44, matricula=123456, nome=João da Silva, " +
                "dataNascimento=2021-04-11, email=joaosilva@gmail.com, cpfCnpj=12345678910, salario=2000.0, " +
                "entradaEmpresa=2023-02-13, saidaEmpresa=null, ocupacao=Técnico Interno, " +
                "tipoOcupacaoEnum=TECNICO_INTERNO, modeloContratacaoEnum=CLT, modeloTrabalhoEnum=PRESENCIAL, " +
                "statusColaboradorEnum=ATIVO, fotoPerfil=null, exclusao=ExclusaoEntity(id=null, dataExclusao=null, " +
                "horaExclusao=null, responsavelExclusao=null), " +
                "acessoSistema=AcessoSistemaResponse(acessoSistemaAtivo=true, " +
                "permissaoEnum=LEITURA_AVANCADA_ALTERACAO), configuracaoPerfil=null, contratoContratacao=null, " +
                "endereco=EnderecoEntity(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, " +
                "bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, complemento=Casa 4, estado=SP), " +
                "telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefone=MOVEL_WHATSAPP), " +
                "expediente=null, dispensa=null, pontos=[], historicoFerias=[], advertencias=[]), " +
                "ColaboradorResponse(id=1, dataCadastro=2023-02-13, horaCadastro=10:44, matricula=123456, " +
                "nome=João da Silva, dataNascimento=2021-04-11, email=joaosilva@gmail.com, cpfCnpj=12345678910, " +
                "salario=2000.0, entradaEmpresa=2023-02-13, saidaEmpresa=null, ocupacao=Técnico Interno, " +
                "tipoOcupacaoEnum=TECNICO_INTERNO, modeloContratacaoEnum=CLT, modeloTrabalhoEnum=PRESENCIAL, " +
                "statusColaboradorEnum=ATIVO, fotoPerfil=null, exclusao=ExclusaoEntity(id=null, dataExclusao=null, " +
                "horaExclusao=null, responsavelExclusao=null), " +
                "acessoSistema=AcessoSistemaResponse(acessoSistemaAtivo=true, " +
                "permissaoEnum=LEITURA_AVANCADA_ALTERACAO), configuracaoPerfil=null, contratoContratacao=null, " +
                "endereco=null, telefone=null, expediente=null, dispensa=null, pontos=[], historicoFerias=[], " +
                "advertencias=[])], empty=false, first=true, last=true, number=0, numberOfElements=2, offset=0, " +
                "pageNumber=0, pageSize=10, paged=true, unpaged=false, size=10, totalElements=2, totalPages=1)",
                colaboradorPageResponse.toString());
    }

    @Test
    @DisplayName("Deve testar método de conversão de colaborador Entity para colaborador Response")
    void deveTestarMetodoDeConversaoDeColaboradorEntityParaColaboradorResponse() {
        ColaboradorEntity colaboradorEntity = ColaboradorEntityBuilder.builder()
                .comAcessoCompleto()
                .comEmpresa()
                .comTelefone()
                .comEndereco()
                .comExclusao()
                .build();

        ColaboradorResponse colaboradorResponse = colaboradorTypeConverter
                .converteColaboradorEntityParaColaboradorResponse(colaboradorEntity);

        Assertions.assertEquals("ColaboradorResponse(id=1, dataCadastro=2023-02-13, horaCadastro=10:44, " +
                "matricula=123456, nome=João da Silva, dataNascimento=2021-04-11, email=joaosilva@gmail.com, " +
                "cpfCnpj=12345678910, salario=2000.0, entradaEmpresa=2023-02-13, saidaEmpresa=null, " +
                "ocupacao=Técnico Interno, tipoOcupacaoEnum=TECNICO_INTERNO, modeloContratacaoEnum=CLT, " +
                "modeloTrabalhoEnum=PRESENCIAL, statusColaboradorEnum=ATIVO, fotoPerfil=null, " +
                "exclusao=ExclusaoEntity(id=null, dataExclusao=null, horaExclusao=null, responsavelExclusao=null), " +
                "acessoSistema=AcessoSistemaResponse(acessoSistemaAtivo=true, " +
                "permissaoEnum=LEITURA_AVANCADA_ALTERACAO), configuracaoPerfil=null, contratoContratacao=null, " +
                "endereco=EnderecoEntity(id=1, logradouro=Avenida Coronel Manuel Py, numero=583, " +
                "bairro=Lauzane Paulista, codigoPostal=02442-090, cidade=São Paulo, complemento=Casa 4, estado=SP), " +
                "telefone=TelefoneEntity(id=1, prefixo=11, numero=979815415, tipoTelefone=MOVEL_WHATSAPP), " +
                "expediente=null, dispensa=null, pontos=[], historicoFerias=[], advertencias=[])",
                colaboradorResponse.toString());
    }

}
