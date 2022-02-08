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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<TicketDto> create(@Valid @RequestBody TicketRequest request) {
        TicketDto ticketDto = ticketMapper.mapToTicketDto(ticketService.create(ticketMapper.mapToTicket(request)));
        return new ResponseEntity<>(ticketDto, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation("Read all tickets")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Page<TicketDto>> readAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<TicketDto> ticketDtoList = ticketService.readAll(pageable)
                                                     .map((ticketMapper::mapToTicketDto));
        return new ResponseEntity<>(ticketDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Read ticket by id")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<TicketDto> readById(@PathVariable Long id) {
        TicketDto ticketDto = ticketMapper.mapToTicketDto(ticketService.readById(id));
        return new ResponseEntity<>(ticketDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ApiOperation("update ticket")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<TicketDto> update(@Valid @PathVariable Long id,
            @RequestBody TicketRequest request) {
        TicketDto ticketDto = ticketMapper.mapToTicketDto(ticketService.update(id,
                ticketMapper.mapToTicket(request)));
        return new ResponseEntity<>(ticketDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete ticket")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<TicketDto> delete(@PathVariable Long id) {
        ticketService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
