package com.sehbeomschool.nova.domain.user.service;

import com.sehbeomschool.nova.domain.user.dao.UserRepository;
import com.sehbeomschool.nova.domain.user.domain.CustomUserDetails;
import com.sehbeomschool.nova.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.valueOf(userId)).orElseThrow();
        return CustomUserDetails.builder().user(user).build();
    }
}
