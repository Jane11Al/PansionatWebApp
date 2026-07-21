package ru.ssau.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ssau.dto.PupilDto;
import ru.ssau.entity.Pupil;
import ru.ssau.exception.EntityNotFoundException;
import ru.ssau.mapper.PupilMapper;
import ru.ssau.repository.PupilRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PupilServiceTest {

    @Mock
    private PupilRepository repository;

    @Mock
    private PupilMapper mapper;

    @InjectMocks
    private PupilService service;

    private Pupil pupil;
    private PupilDto pupilDto;

    @BeforeEach
    void setUp() {
        pupil = new Pupil();
        pupil.setId(1L);
        pupil.setPersonalFileNumber(1001);
        pupil.setFullName("Иванов Иван");

        pupilDto = new PupilDto();
        pupilDto.setId(1L);
        pupilDto.setPersonalFileNumber(1001);
        pupilDto.setFullName("Иванов Иван");
    }

    @Test
    void findAll_ShouldReturnListOfDtos() {
        when(repository.findAll()).thenReturn(List.of(pupil));
        when(mapper.toDtoList(any())).thenReturn(List.of(pupilDto));

        List<PupilDto> result = service.findAll();

        assertEquals(1, result.size());
        assertEquals(pupilDto, result.get(0));
        verify(repository).findAll();
        verify(mapper).toDtoList(List.of(pupil));
    }

    @Test
    void findById_WhenExists_ShouldReturnDto() {
        when(repository.findById(1L)).thenReturn(Optional.of(pupil));
        when(mapper.toDto(pupil)).thenReturn(pupilDto);

        PupilDto result = service.findById(1L);

        assertEquals(pupilDto, result);
        verify(repository).findById(1L);
        verify(mapper).toDto(pupil);
    }

    @Test
    void findById_WhenNotExists_ShouldThrowEntityNotFoundException() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.findById(99L));
        verify(repository).findById(99L);
        verifyNoInteractions(mapper);
    }

    @Test
    void create_ShouldSaveAndReturnDto() {
        when(mapper.toEntity(pupilDto)).thenReturn(pupil);
        when(repository.save(pupil)).thenReturn(pupil);
        when(mapper.toDto(pupil)).thenReturn(pupilDto);

        PupilDto result = service.create(pupilDto);

        assertEquals(pupilDto, result);
        verify(mapper).toEntity(pupilDto);
        verify(repository).save(pupil);
        verify(mapper).toDto(pupil);
    }

    @Test
    void update_WhenExists_ShouldUpdateAndReturnDto() {
        PupilDto updatedDto = new PupilDto();
        updatedDto.setFullName("Петров Пётр");

        when(repository.findById(1L)).thenReturn(Optional.of(pupil));
        doAnswer(invocation -> {
            pupil.setFullName("Петров Пётр");
            return null;
        }).when(mapper).updateEntity(eq(updatedDto), eq(pupil));
        when(repository.save(pupil)).thenReturn(pupil);
        when(mapper.toDto(pupil)).thenReturn(updatedDto);

        PupilDto result = service.update(1L, updatedDto);

        assertEquals(updatedDto, result);
        verify(repository).findById(1L);
        verify(mapper).updateEntity(updatedDto, pupil);
        verify(repository).save(pupil);
        verify(mapper).toDto(pupil);
    }

    @Test
    void update_WhenNotExists_ShouldThrowEntityNotFoundException() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.update(99L, pupilDto));
        verify(repository).findById(99L);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper);
    }

    @Test
    void delete_WhenExists_ShouldCallRepositoryDelete() {
        when(repository.existsById(1L)).thenReturn(true);

        service.delete(1L);

        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void delete_WhenNotExists_ShouldThrowEntityNotFoundException() {
        when(repository.existsById(99L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> service.delete(99L));
        verify(repository).existsById(99L);
        verify(repository, never()).deleteById(any());
    }
}