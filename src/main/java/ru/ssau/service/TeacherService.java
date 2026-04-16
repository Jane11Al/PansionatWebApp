package ru.ssau.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.dto.TeacherDto;
import ru.ssau.entity.Teacher;
import ru.ssau.exception.EntityNotFoundException;
import ru.ssau.mapper.TeacherMapper;
import ru.ssau.repository.TeacherRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService implements BaseService<TeacherDto, Long> {

    private final TeacherRepository repository;
    private final TeacherMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<TeacherDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public TeacherDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Педагог", id));
    }

    @Override
    @Transactional
    public TeacherDto create(TeacherDto dto) {
        Teacher entity = mapper.toEntity(dto);
        // Пароль должен быть установлен отдельно, например при регистрации
        return mapper.toDto(repository.save(entity));
    }

    @Override
    @Transactional
    public TeacherDto update(Long id, TeacherDto dto) {
        Teacher existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Педагог", id));
        mapper.updateEntity(dto, existing);
        return mapper.toDto(repository.save(existing));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Педагог", id);
        }
        repository.deleteById(id);
    }

    @Transactional
    public void updatePassword(Long id, String rawPassword) {
        Teacher teacher = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Педагог", id));
        teacher.setPassword(passwordEncoder.encode(rawPassword));
        repository.save(teacher);
    }
}