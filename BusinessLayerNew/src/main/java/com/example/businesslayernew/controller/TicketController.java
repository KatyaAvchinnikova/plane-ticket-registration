package com.example.businesslayernew.controller;

import com.example.businesslayernew.dto.ticket.TicketDto;
import com.example.businesslayernew.dto.ticket.TicketRequest;
import com.example.businesslayernew.mapper.TicketMapper;
import com.example.businesslayernew.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Getter
@Setter
@RequestMapping("/api/tickets")
@Api("Tickets controller")
public class TicketController {

    private final TicketService ticketService;

    private final TicketMapper ticketMapper;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create new ticket")
    @ResponseStatus(HttpStatus.CREATED)
    public TicketDto create(@Valid @RequestBody TicketRequest request) {
        return ticketMapper.mapToTicketDto(ticketService.create(ticketMapper.mapToTicket(request)));
    }

    //    Возвращаем респонсЕнтити, на вход Pageable с аннотацией @PageableDefault
    @GetMapping(params = {"page", "size"})
    @ApiOperation("Read all tickets")
    @ResponseStatus(HttpStatus.OK)
    public Page<TicketDto> readAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ticketService.readAll(pageable)
                            .map((ticketMapper::mapToTicketDto));
    }

    @GetMapping("/{id}")
    @ApiOperation("Read ticket by id")
    @ResponseStatus(HttpStatus.OK)
    public TicketDto readById(@PathVariable Long id) {
        return ticketMapper.mapToTicketDto(ticketService.readById(id));
    }

    @PatchMapping("/{id}")
    @ApiOperation("update ticket")
//    TODO: к общему виду респонсы. Везде РеспонсЕнтити, если его юзаешь
    public TicketDto update(@Valid @PathVariable Long id,
            @RequestBody TicketRequest request) {
        return ticketMapper.mapToTicketDto(ticketService.update(id,
                ticketMapper.mapToTicket(request)));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete ticket")
    public ResponseEntity<TicketDto> delete(@PathVariable Long id) {
        ticketService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
