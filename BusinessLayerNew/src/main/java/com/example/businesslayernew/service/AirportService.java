package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.AirportEntity;
import com.example.businesslayernew.exception.ResourceNotFoundException;
import com.example.businesslayernew.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "airports")
    public AirportEntity create(AirportEntity airport) {
        //        TODO: возвращаемое значение - результат сохранения
        return airportRepository.save(airport);
    }

    //    TODO: getById. read->get
    @Cacheable(value = "airports")
    public AirportEntity getById(Long id) {
        return airportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURSENAME,
                FIELDNAME, id));
    }

    public List<AirportEntity> getAll(int page, int size) {

        Pageable pageSize = PageRequest.of(page - 1, size);

        return airportRepository.findAll(pageSize).toList();
    }

    @CachePut(value = "airports", key = "#airport.id")
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
    @CacheEvict(value = "airports")
    public void delete(Long id) {
        if (airportRepository.findById(id) == null) {
            throw new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id);
        } else {
            airportRepository.deleteById(id);
        }
     }
}
