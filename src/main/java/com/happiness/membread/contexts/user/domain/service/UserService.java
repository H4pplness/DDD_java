package com.happiness.membread.contexts.user.domain.service;

import com.happiness.membread.contexts.user.database.entities.User;
import com.happiness.membread.contexts.user.database.repositories.UserRepository;
import com.happiness.membread.contexts.user.domain.aggregates.info.UserInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class UserService {
    UserRepository userRepository;

    public UserInfo getUserInfo(String id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            Map<String,Object> attributes = ((JwtAuthenticationToken)authentication).getTokenAttributes();

            User user = new User();
            user.setId(id);
            user.setFirstName((String)attributes.get("given_name"));
            user.setLastName((String)attributes.get("family_name"));
            user.setEmail((String)attributes.get("email"));
            userRepository.save(user);

            return new UserInfo(user.getId(),user.getFirstName(),user.getLastName(),user.getEmail());
        }
        User user = userOptional.get();
        return new UserInfo(user.getId(),user.getFirstName(),user.getLastName(),user.getEmail());
    }

    public UserInfo updateUserInfo(String id,UserInfo userInfo){
        User user = userRepository.getReferenceById(id);
        if(user.getFirstName() != null) user.setFirstName(user.getFirstName());
        if(user.getLastName() != null) user.setLastName(user.getLastName());
        if(user.getEmail()!=null) user.setEmail(user.getEmail());

        userRepository.save(user);

        return userInfo;
    }
}
