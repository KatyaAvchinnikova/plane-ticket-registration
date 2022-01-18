package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.AirportEntity;
import com.example.businesslayernew.exception.ResourceNotFoundException;
import com.example.businesslayernew.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final String RESOURSENAME= "Airport";

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

    public List<AirportEntity> getAll() {
        List<AirportEntity> entityList = airportRepository.findAll();
        return airportRepository.findAll();
    }

    public AirportEntity update(Long id, AirportEntity airport) {
//       TODO:  почему не
//        return Optional.of(airportRepository.getById(id)).map(airportRepository::save).orElseThrow(
//                () -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id)); ?
//        Optional.of(airportRepository.getById(id)).orElseThrow(
//                () -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id));
        airport.setId(id);
        return airportRepository.save(airport);
//        return Optional.of(airportRepository.getById(id)).map(airportRepository::save).orElseThrow(
//                () -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id));
    }

    @Transactional
    public void delete(Long id) {
        Optional.of(airportRepository.getById(id)).orElseThrow(
                () -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id));
        airportRepository.deleteById(id);

    }

}
