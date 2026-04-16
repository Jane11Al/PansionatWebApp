package ru.ssau.service;

import ru.ssau.entity.Teacher;
import ru.ssau.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class TeacherUserDetailsService implements UserDetailsService {

    private final TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String fullName) throws UsernameNotFoundException {
        Teacher teacher = teacherRepository.findByFullName(fullName)
                .orElseThrow(() -> new UsernameNotFoundException("Педагог с ФИО '" + fullName + "' не найден"));

        return new User(
                teacher.getFullName(),
                teacher.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(teacher.getRole()))
        );
    }
}