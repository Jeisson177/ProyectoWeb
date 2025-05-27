package com.example.demo.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Administrador;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Operador;
import com.example.demo.entity.Role;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Buscar al usuario si no se encuentra traer una excepcion
        //El usuario que se carga es de tipo USer Entity, el que nosotros creamos
       UserEntity userDB = userRepository.findByUsername(username).orElseThrow(
           () -> new UsernameNotFoundException("User not found")
       );
       UserDetails userDetails = new User(userDB.getUsername(),
        userDB.getPassword(),
         mapToGrantedAuthorities(userDB.getRoles()));

        return userDetails;
    }

    //El usuario que se retorna es de tipo UserDetail
        //Se mapean los datos desde el UserEntity a UserDetail
        //Es necesario pasar como tercer parametro grantedAutathorities
    private Collection<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
       return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public UserEntity ClienteToUser(Cliente cliente){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(cliente.getCorreo());
        userEntity.setPassword(passwordEncoder.encode("123"));

        Role roles = roleRepository.findByName("CLIENTE").get();
        userEntity.setRoles(List.of(roles));

        return userEntity;
    }

    public UserEntity AdminToUser(Administrador administrador){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(administrador.getUsuario()); 
        userEntity.setPassword(passwordEncoder.encode("123"));

        Role roles = roleRepository.findByName("ADMIN").get();
        userEntity.setRoles(List.of(roles));

        return userEntity;
    }

    public UserEntity OperadorToUser(Operador operador){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(operador.getUsuario()); 
        userEntity.setPassword(passwordEncoder.encode("123"));

        Role roles = roleRepository.findByName("OPERADOR").get();
        userEntity.setRoles(List.of(roles));

        return userEntity;
    }
 
}
