package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.AirportEntity;
import com.example.businesslayernew.exception.ResourceNotFoundException;
import com.example.businesslayernew.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AirportService {

    @Autowired
    private final AirportRepository airportRepository;
    //    Слова разделяются _. Пустая строка между полями с разными назначениями. Константы над инжектируемыми полями
    private static final String RESOURSENAME = "Airport";

    private static final String FIELDNAME = "Id";

    @Transactional
    public AirportEntity create(AirportEntity airport) {
        //        TODO: возвращаемое значение - результат сохранения
        return airportRepository.save(airport);
    }

    //    TODO: getById. read->get
    public AirportEntity getById(Long id) {
        return airportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURSENAME,
                FIELDNAME, id));
    }

    public List<AirportEntity> getAll(int page, int size) {

        Pageable pageSize = PageRequest.of(page - 1, size);

        return airportRepository.findAll(pageSize).toList();
    }

    public AirportEntity update(Long id, AirportEntity airport) {
//       TODO:  почему не
        //Потому что в параметры метода save будет передан не смапенный из реквеста объект, а существующий в базе
//      return Optional.of(airportRepository.(id)).map(airportRepository::save).orElseThrow(
//                () -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id)); ?
        if (airportRepository.findById(id) == null) {
            throw new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id);
        } else {
            airport.setId(id);
        }
        return airportRepository.save(airport);
    }

    @Transactional
    public void delete(Long id) {
        Optional.of(airportRepository.getById(id)).orElseThrow(
                () -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id));
        airportRepository.deleteById(id);
    }

}
