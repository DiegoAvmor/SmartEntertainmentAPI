package com.innercircle.api.service;

import java.util.Optional;
import com.innercircle.api.exceptions.AlreadyRegistered;
import com.innercircle.api.exceptions.NotFoundException;
import com.innercircle.api.model.User;
import com.innercircle.api.model.dto.TokenDto;
import com.innercircle.api.model.request.LoginUserRequest;
import com.innercircle.api.model.request.RegisterUserRequest;
import com.innercircle.api.repository.UserRepository;
import com.innercircle.api.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    public TokenDto loadUser(LoginUserRequest request) {
        Optional<User> opt = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        if (opt.isPresent()) {
            User user = opt.get();
            //Se crea el token
            TokenDto token = new TokenDto();
            token.setToken(jwtTokenUtil.generateToken(user));
            return token;
        }
        throw new NotFoundException("El usuario no fue encontrado");
    }


    public User registerUser(RegisterUserRequest request){
        Optional<User> opt = userRepository.findByEmail(request.getEmail());
        if (!opt.isPresent()){
            User nuevoUser = new User();
            nuevoUser.setName(request.getName());
            nuevoUser.setEmail(request.getEmail());
            nuevoUser.setPassword(request.getPassword());
            nuevoUser = userRepository.save(nuevoUser);
            return nuevoUser;
        }
        throw new AlreadyRegistered("Ya existe una cuenta asociada a ese correo");
    }
       
}

