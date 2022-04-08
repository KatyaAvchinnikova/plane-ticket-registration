package com.innowise.businesslayer.controller;

import com.innowise.businesslayer.dto.ticket.TicketDto;
import com.innowise.businesslayer.dto.ticket.TicketRequest;
import com.innowise.businesslayer.mapper.TicketMapper;
import com.innowise.businesslayer.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/tickets")
@Tag(name = "Tickets controller")
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @PostMapping
    @Operation(summary = "Create new ticket")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<TicketDto> create(@Valid @RequestBody TicketRequest request) {
        TicketDto ticketDto = ticketMapper.mapToTicketDto(ticketService.create(ticketMapper.mapToTicket(request)));
        return new ResponseEntity<>(ticketDto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Read all tickets")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Page<TicketDto>> readAll(@ParameterObject Pageable pageable) {
        Page<TicketDto> ticketDtoList = ticketService.readAll(pageable)
                                                     .map((ticketMapper::mapToTicketDto));
        return new ResponseEntity<>(ticketDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read ticket by id")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<TicketDto> readById(@PathVariable Long id) {
        TicketDto ticketDto = ticketMapper.mapToTicketDto(ticketService.readById(id));
        return new ResponseEntity<>(ticketDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "update ticket")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<TicketDto> update(@Valid @PathVariable Long id,
            @RequestBody TicketRequest request) {
        TicketDto ticketDto = ticketMapper.mapToTicketDto(ticketService.update(id,
                ticketMapper.mapToTicket(request)));
        return new ResponseEntity<>(ticketDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete ticket")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<TicketDto> delete(@PathVariable Long id) {
        ticketService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
