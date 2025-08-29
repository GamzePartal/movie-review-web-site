package com.webproject.web.service.impl;
import com.webproject.web.dto.DtoUser;
import com.webproject.web.entity.Role;
import com.webproject.web.entity.User;
import com.webproject.web.excepiton.BaseException;
import com.webproject.web.excepiton.ErrorMessage;
import com.webproject.web.excepiton.MessageType;
import com.webproject.web.jwt.AuthRequest;
import com.webproject.web.jwt.AuthResponse;
import com.webproject.web.jwt.JwtService;
import com.webproject.web.repository.IRoleRepository;
import com.webproject.web.repository.IUserRepository;
import com.webproject.web.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {


    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final org.modelmapper.ModelMapper modelMapper;


    @Override
    public DtoUser register(AuthRequest request) {
        Role role = roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "role bulunamadı")));

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getEmail().split("@")[0]); //emailden username çıkar
        user.setRole(role);

        User saved = userRepository.save(user);
        return  modelMapper.map(saved, DtoUser.class);
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Kullanıcı bulunamadı")));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Şifre hatalı");
        }


        String token = jwtService.generateToken(user);
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        return response;

    }
}
