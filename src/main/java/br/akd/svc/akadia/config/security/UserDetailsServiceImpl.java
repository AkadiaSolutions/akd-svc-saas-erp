package br.akd.svc.akadia.config.security;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.repositories.sistema.colaboradores.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
        Optional<ColaboradorEntity> colaborador = colaboradorRepository.buscaPorMatricula(matricula);
        if (colaborador.isPresent()) {
            return new UserSS(colaborador.get().getId(),
                    colaborador.get().getMatricula(),
                    colaborador.get().getAcessoSistema().getSenhaCriptografada(),
                    colaborador.get().getAcessoSistema().getPrivilegios());
        }
        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}
