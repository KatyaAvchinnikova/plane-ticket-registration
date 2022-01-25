package com.example.businesslayernew.controller;

import com.example.businesslayernew.dto.ticket.TicketRequest;
import com.example.businesslayernew.dto.ticket.TicketResponse;
import com.example.businesslayernew.mapper.TicketMapper;
import com.example.businesslayernew.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
    public TicketResponse create(@Valid @RequestBody TicketRequest request) {
        return ticketMapper.mapToTicketDto(ticketService.create(ticketMapper.mapToTicket(request)));

    }

//    Возвращаем респонсЕнтити, на вход Pageable с аннотацией @PageableDefault
    @GetMapping(params = {"page", "size"})
    @ApiOperation("Read all tickets")
    @ResponseStatus(HttpStatus.OK)
    public List<TicketResponse> readAll(@RequestParam("page") int page,
            @RequestParam("size") int size) {
        return ticketService.readAll(page, size).stream().map((ticketMapper::mapToTicketDto)).collect(
                Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiOperation("Read ticket by id")
    @ResponseStatus(HttpStatus.OK)
    public TicketResponse readById(@PathVariable Long id) {
        return ticketMapper.mapToTicketDto(ticketService.readById(id));
    }

    @PatchMapping("/{id}")
    @ApiOperation("update ticket")
//    TODO: к общему виду респонсы. Везде РеспонсЕнтити, если его юзаешь
    public TicketResponse update(@Valid @PathVariable Long id,
            @RequestBody TicketRequest request) {
        return ticketMapper.mapToTicketDto(ticketService.update(id,
                ticketMapper.mapToTicket(request)));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete ticket")
    public ResponseEntity<TicketResponse> delete(@PathVariable Long id) {
        ticketService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
