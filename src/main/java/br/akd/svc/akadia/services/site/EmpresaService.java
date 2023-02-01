package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.dto.site.EmpresaDto;
import br.akd.svc.akadia.models.entities.site.EmpresaEntity;
import br.akd.svc.akadia.repositories.site.impl.EmpresaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    @Autowired
    EmpresaRepositoryImpl empresaRepositoryImpl;

    public EmpresaEntity criaNovaEmpresa(EmpresaDto empresaDto) {

        return new EmpresaEntity();
    }

}
