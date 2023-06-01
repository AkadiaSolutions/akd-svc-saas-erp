package br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks;

import br.akd.svc.akadia.models.entities.global.mocks.EnderecoEntityBuilder;
import br.akd.svc.akadia.models.entities.global.mocks.TelefoneEntityBuilder;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.AcessoSistemaEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.models.entities.site.empresa.mocks.EmpresaEntityBuilder;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

public class ColaboradorEntityBuilder {

    ColaboradorEntityBuilder() {
    }

    ColaboradorEntity colaboradorEntity;

    public static ColaboradorEntityBuilder builder() {
        ColaboradorEntityBuilder builder = new ColaboradorEntityBuilder();
        builder.colaboradorEntity = new ColaboradorEntity();
        builder.colaboradorEntity.setId(1L);
        builder.colaboradorEntity.setDataCadastro(LocalDate.of(2023, 2, 13).toString());
        builder.colaboradorEntity.setHoraCadastro(LocalTime.of(10, 44).toString());
        builder.colaboradorEntity.setMatricula("123456");
        builder.colaboradorEntity.setNome("João da Silva");
        builder.colaboradorEntity.setDataNascimento("2021-04-11");
        builder.colaboradorEntity.setEmail("joaosilva@gmail.com");
        builder.colaboradorEntity.setCpfCnpj("12345678910");
        builder.colaboradorEntity.setSalario(2000.0);
        builder.colaboradorEntity.setEntradaEmpresa(LocalDate.of(2023, 2, 13).toString());
        builder.colaboradorEntity.setSaidaEmpresa(null);
        builder.colaboradorEntity.setOcupacao("Técnico Interno");
        builder.colaboradorEntity.setTipoOcupacaoEnum(TipoOcupacaoEnum.TECNICO_INTERNO);
        builder.colaboradorEntity.setModeloContratacaoEnum(ModeloContratacaoEnum.CLT);
        builder.colaboradorEntity.setModeloTrabalhoEnum(ModeloTrabalhoEnum.PRESENCIAL);
        builder.colaboradorEntity.setStatusColaboradorEnum(StatusColaboradorEnum.ATIVO);
        builder.colaboradorEntity.setFotoPerfil(null);
        builder.colaboradorEntity.setContratoContratacao(null);
        builder.colaboradorEntity.setExclusao(null);
        builder.colaboradorEntity.setAcessoSistema(null);
        builder.colaboradorEntity.setConfiguracaoPerfil(null);
        builder.colaboradorEntity.setEndereco(null);
        builder.colaboradorEntity.setTelefone(null);
        builder.colaboradorEntity.setExpediente(null);
        builder.colaboradorEntity.setDispensa(null);
        builder.colaboradorEntity.setPontos(new ArrayList<>());
        builder.colaboradorEntity.setHistoricoFerias(new ArrayList<>());
        builder.colaboradorEntity.setAdvertencias(new ArrayList<>());
        builder.colaboradorEntity.setAcessos(new ArrayList<>());
        builder.colaboradorEntity.setAcoes(new ArrayList<>());
        builder.colaboradorEntity.setEmpresa(null);
        return builder;
    }

    public ColaboradorEntityBuilder comEmpresa() {
        colaboradorEntity.setEmpresa(EmpresaEntityBuilder.builder().build());
        return this;
    }

    public ColaboradorEntityBuilder comAcessoCompleto() {
        colaboradorEntity.setAcessoSistema(AcessoSistemaEntity.builder()
                .acessoSistemaAtivo(true)
                .senha("123")
                .senhaCriptografada("10283718293718293")
                .privilegios(new HashSet<>())
                .permissaoEnum(PermissaoEnum.LEITURA_AVANCADA_ALTERACAO)
                .build());
        return this;
    }

    public ColaboradorEntityBuilder comAdvertencia() {
        colaboradorEntity.getAdvertencias().add(AdvertenciaEntityBuilder.builder().build());
        return this;
    }

    public ColaboradorEntityBuilder comAdvertenciaComArquivo() {
        colaboradorEntity.getAdvertencias().add(AdvertenciaEntityBuilder.builder().comArquivo().build());
        return this;
    }

    public ColaboradorEntityBuilder comEndereco() {
        this.colaboradorEntity.setEndereco(EnderecoEntityBuilder.builder().build());
        return this;
    }

    public ColaboradorEntityBuilder comTelefone() {
        this.colaboradorEntity.setTelefone(TelefoneEntityBuilder.builder().build());
        return this;
    }

    public ColaboradorEntity build() {
        return colaboradorEntity;
    }
}
