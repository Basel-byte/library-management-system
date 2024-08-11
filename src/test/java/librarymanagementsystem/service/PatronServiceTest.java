package librarymanagementsystem.service;

import librarymanagementsystem.dto.PatronDto;
import librarymanagementsystem.mapper.PatronMapper;
import librarymanagementsystem.model.Patron;
import librarymanagementsystem.repository.PatronRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PatronServiceTest {

    @Mock
    private PatronRepository repository;
    @Mock
    private PatronMapper mapper;

    private PatronService patronService;

    @BeforeEach
    void setUp() {
        patronService = new PatronService(repository, mapper);
    }

    @Test
    void getAllPatrons() {
        // given
        List<Patron> patrons = List.of(
                new Patron("Basel Ahmed Awad", "11 Bikingham Street, Alexandria", "+201097453125","basel@gmail.com")
        );
        List<PatronDto> patronDtos = List.of(
                new PatronDto(1L, "Basel Ahmed Awad", "11 Bikingham Street, Alexandria", "+201097453125","basel@gmail.com")
        );
        given(repository.findAll()).willReturn(patrons);
        given(mapper.patronsToPatronDtos(patrons)).willReturn(patronDtos);

        // when
        List<PatronDto> patronDtoList = patronService.getAllPatrons();

        // then
        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).patronsToPatronDtos(patrons);
        assertThat(patronDtoList).isEqualTo(patronDtos);
    }

    @Test
    void getPatron() {
        // given
        Long id = 1L;
        Optional<Patron> optionalPatron = Optional.of(
                new Patron("Basel Ahmed Awad", "11 Bikingham Street, Alexandria", "+201097453125","basel@gmail.com")
        );
        PatronDto patronDto = new PatronDto(1L, "Basel Ahmed Awad", "11 Bikingham Street, Alexandria", "+201097453125","basel@gmail.com");
        given(repository.findById(id)).willReturn(optionalPatron);
        given(mapper.patronToPatronDto(optionalPatron.get())).willReturn(patronDto);

        // when
        PatronDto returnedPatronDto = patronService.getPatron(id);

        // then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(repository, times(1)).findById(longArgumentCaptor.capture());
        verify(mapper, times(1)).patronToPatronDto(optionalPatron.get());
        assertThat(returnedPatronDto).isEqualTo(patronDto);
        assertThat(longArgumentCaptor.getValue()).isEqualTo(id);
    }

    @Test
    void addPatron() {
        // given
        Patron patron = new Patron("Basel Ahmed Awad", "11 Bikingham Street, Alexandria", "+201097453125","basel@gmail.com");
        PatronDto patronDto = new PatronDto(1L, "Basel Ahmed Awad", "11 Bikingham Street, Alexandria", "+201097453125","basel@gmail.com");
        given(mapper.patronDtoToPatron(patronDto)).willReturn(patron);
        given(repository.save(patron)).willReturn(any());

        // when
        patronService.addPatron(patronDto);

        // then
        ArgumentCaptor<PatronDto> argumentCaptor = ArgumentCaptor.forClass(PatronDto.class);
        verify(mapper, times(1)).patronDtoToPatron(argumentCaptor.capture());
        verify(repository, times(1)).save(patron);
        assertThat(argumentCaptor.getValue()).isEqualTo(patronDto);
    }

    @Test
    void updatePatron() {
        // given
        Long id = 1L;
        Patron patron = new Patron("Basel Ahmed Awad", "11 Bikingham Street, Alexandria", "+201097453125","basel@gmail.com");
        PatronDto patronDto = new PatronDto(1L, "Basel Ahmed Awad", "11 Bikingham Street, Alexandria", "+201097453125","basel@gmail.com");
        given(repository.findById(id)).willReturn(Optional.of(patron));
        given(repository.save(patron)).willReturn(any());

        // when
        patronService.updatePatron(id, patronDto);

        // then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<PatronDto> patronDtoArgumentCaptor = ArgumentCaptor.forClass(PatronDto.class);
        ArgumentCaptor<Patron> patronArgumentCaptor = ArgumentCaptor.forClass(Patron.class);
        verify(repository, times(1)).findById(longArgumentCaptor.capture());
        verify(mapper, times(1)).updatePatronFromPatronDto(patronDtoArgumentCaptor.capture(), patronArgumentCaptor.capture());
        verify(repository, times(1)).save(patron);
        assertThat(patronDtoArgumentCaptor.getValue()).isEqualTo(patronDto);
        assertThat(longArgumentCaptor.getValue()).isEqualTo(id);
    }

    @Test
    void deletePatron() {
        // given
        Long id = 1L;
        Patron patron = new Patron("Basel Ahmed Awad", "11 Bikingham Street, Alexandria", "+201097453125","basel@gmail.com");
        given(repository.findById(id)).willReturn(Optional.of(patron));

        // when
        patronService.deletePatron(id);

        // then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(repository, times(1)).findById(longArgumentCaptor.capture());
        verify(repository, times(1)).delete(patron);
        assertThat(longArgumentCaptor.getValue()).isEqualTo(id);
    }
}