package com.example.businesslayernew.service;

import com.example.businesslayernew.domain.AirportEntity;
import com.example.businesslayernew.exception.ResourceNotFoundException;
import com.example.businesslayernew.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AirportService implements TicketRegistrationService<AirportEntity, Long>{
    @Autowired
    private final AirportRepository airportRepository;
//    Слова разделяются _. Пустая строка между полями с разными назначениями. Константы над инжектируемыми полями
    private static final String RESOURSENAME= "Airport";

    private static final String FIELDNAME = "Id";

    @Override
    @Transactional
    public AirportEntity create(AirportEntity airport) {
        //        TODO: возвращаемое значение - результат сохранения
        return airportRepository.save(airport);
    }

    @Override
//    TODO: getById. read->get
    public AirportEntity readById(Long id) {

        return airportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURSENAME,
                FIELDNAME, id));
    }

    @Override
    public List<AirportEntity> readAll() {
        List<AirportEntity> entityList = airportRepository.findAll();
        return airportRepository.findAll();
    }

    @Override
    public AirportEntity update(Long id, AirportEntity airport) {
//       TODO:  почему не
//        return Optional.of(airportRepository.getById(id)).map(airportRepository::save).orElseThrow(
//                () -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id)); ?
        Optional.of(airportRepository.getById(id)).orElseThrow(
                () -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id));
        airport.setId(id);
        airportRepository.save(airport);
        return airport;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional.of(airportRepository.getById(id)).orElseThrow(
                () -> new ResourceNotFoundException(RESOURSENAME, FIELDNAME, id));
        airportRepository.deleteById(id);
    }

}
