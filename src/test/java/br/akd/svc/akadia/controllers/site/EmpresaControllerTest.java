package br.akd.svc.akadia.controllers.site;

import br.akd.svc.akadia.models.dto.site.empresa.mocks.EmpresaDtoBuilder;
import br.akd.svc.akadia.models.entities.site.empresa.mocks.CriaEmpresaResponseBuilder;
import br.akd.svc.akadia.models.entities.site.empresa.mocks.EmpresaEntityBuilder;
import br.akd.svc.akadia.repositories.site.impl.EmpresaRepositoryImpl;
import br.akd.svc.akadia.services.site.EmpresaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Controller: Empresa")
class EmpresaControllerTest {

    @InjectMocks
    EmpresaController empresaController;

    @Mock
    EmpresaRepositoryImpl empresaRepositoryImpl;

    @Mock
    EmpresaService empresaService;

    @Test
    @DisplayName("Deve testar método controlador de listagem de todas as empresas cadastradas")
    void deveTestarMetodoControladorDeRecebimentoDeListagemDeTodasAsEmpresasCadastradas() {
        when(empresaRepositoryImpl.buscaTodasEmpresas()).thenReturn(new ArrayList<>());
        Assertions.assertEquals("<200 OK OK,[],[]>",
                empresaController.listaTodasEmpresas().toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de criação de nova empresa")
    void deveTestarMetodoControladorDeCriacaoDeNovaEmpresa() {
        when(empresaService.criaNovaEmpresa(any(), any())).thenReturn(CriaEmpresaResponseBuilder.builder().build());
        Assertions.assertEquals("<201 CREATED Created,CriaEmpresaResponse(idClienteEmpresa=1, " +
                        "colaboradorCriado=null),[]>",
                empresaController.criaEmpresa(1L, EmpresaDtoBuilder.builder().build()).toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de atualização de empresa")
    void deveTestarMetodoControladorDeAtualizacaoDeEmpresa() {
        when(empresaService.atualizaEmpresa(any(), any())).thenReturn(EmpresaEntityBuilder.builder().build());
        Assertions.assertEquals("<200 OK OK,EmpresaEntity(id=1, dataCadastro=2023-02-03, horaCadastro=10:48, " +
                        "nome=Akadia Solutions, razaoSocial=AKADIA LTDA, cnpj=12345678000112, endpoint=akadiasolutions, " +
                        "email=akadia@gmail.com, nomeFantasia=Akadia Solutions, inscricaoEstadual=12345667787867, " +
                        "inscricaoMunicipal=12345667787867, nomeResponsavel=Gabriel Lagrota, " +
                        "cpfResponsavel=47153427821, logo=[], deletada=false, dadosEmpresaDeletada=null, " +
                        "segmentoEmpresaEnum=BATERIA_AUTOMOTIVA, telefone=null, configFiscalEmpresa=null, " +
                        "endereco=null),[]>",
                empresaController.atualizaEmpresa(1L, EmpresaDtoBuilder.builder().build()).toString());
    }

    @Test
    @DisplayName("Deve testar método controlador de remoção de empresa")
    void deveTestarMetodoControladorDeRemocaoDeEmpresa() {
        when(empresaService.removeEmpresa(anyLong())).thenReturn(EmpresaEntityBuilder.builder().build());
        Assertions.assertEquals("<200 OK OK,EmpresaEntity(id=1, dataCadastro=2023-02-03, horaCadastro=10:48, " +
                        "nome=Akadia Solutions, razaoSocial=AKADIA LTDA, cnpj=12345678000112, " +
                        "endpoint=akadiasolutions, email=akadia@gmail.com, nomeFantasia=Akadia Solutions, " +
                        "inscricaoEstadual=12345667787867, inscricaoMunicipal=12345667787867, " +
                        "nomeResponsavel=Gabriel Lagrota, cpfResponsavel=47153427821, logo=[], deletada=false, " +
                        "dadosEmpresaDeletada=null, segmentoEmpresaEnum=BATERIA_AUTOMOTIVA, telefone=null, " +
                        "configFiscalEmpresa=null, endereco=null),[]>",
                empresaController.removeEmpresa(1L).toString());
    }
}
