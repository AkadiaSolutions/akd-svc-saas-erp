package br.akd.svc.akadia.models.entities.sistema.clientes.mocks;

import br.akd.svc.akadia.models.entities.global.mocks.EnderecoEntityBuilder;
import br.akd.svc.akadia.models.entities.global.mocks.ExclusaoEntityBuilder;
import br.akd.svc.akadia.models.entities.global.mocks.TelefoneEntityBuilder;
import br.akd.svc.akadia.modules.erp.clientes.models.entity.ClienteEntity;
import br.akd.svc.akadia.models.entities.sistema.colaboradores.mocks.ColaboradorEntityBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClienteEntityBuilder {

    ClienteEntityBuilder() {
    }

    ClienteEntity clienteEntity;

    public static ClienteEntityBuilder builder() {
        ClienteEntityBuilder builder = new ClienteEntityBuilder();
        builder.clienteEntity = new ClienteEntity();
        builder.clienteEntity.setId(1L);
        builder.clienteEntity.setDataCadastro(LocalDate.of(2023, 2, 27).toString());
        builder.clienteEntity.setHoraCadastro(LocalTime.of(17, 40).toString());
        builder.clienteEntity.setDataNascimento("1998-07-21");
        builder.clienteEntity.setNome("Gabriel Lagrota");
        builder.clienteEntity.setCpfCnpj("582.645.389-32");
        builder.clienteEntity.setInscricaoEstadual("145574080114");
        builder.clienteEntity.setEmail("gabrielafonso@mail.com.br");
        builder.clienteEntity.setExclusao(null);
        builder.clienteEntity.setEndereco(null);
        builder.clienteEntity.setTelefone(null);
        builder.clienteEntity.setColaboradorResponsavel(null);
        builder.clienteEntity.setEmpresa(null);
        return builder;
    }

    public ClienteEntityBuilder comColaborador() {
        this.clienteEntity.setColaboradorResponsavel(ColaboradorEntityBuilder.builder().build());
        return this;
    }

    public ClienteEntityBuilder comExclusao(Boolean excluido) {
        if (excluido) this.clienteEntity.setExclusao(ExclusaoEntityBuilder.builder().comExclusao().build());
        else this.clienteEntity.setExclusao(null);
        return this;
    }

    public ClienteEntityBuilder comEndereco() {
        this.clienteEntity.setEndereco(EnderecoEntityBuilder.builder().build());
        return this;
    }

    public ClienteEntityBuilder comTelefone() {
        this.clienteEntity.setTelefone(TelefoneEntityBuilder.builder().build());
        return this;
    }

    public ClienteEntity build() {
        return this.clienteEntity;
    }

}
