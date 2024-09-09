package br.ufscar.dc.dsw.SistemaVagas.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.ufscar.dc.dsw.SistemaVagas.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.SistemaVagas.domain.Usuario;

public class CustomUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUsuarioDAO dao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = dao.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new CustomUserDetails(usuario);
    }
}
