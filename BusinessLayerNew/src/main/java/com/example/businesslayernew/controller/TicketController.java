package com.example.businesslayernew.controller;

import com.example.businesslayernew.dto.request.TicketRequestDto;
import com.example.businesslayernew.dto.request.UserRequestDto;
import com.example.businesslayernew.dto.response.TicketResponseDto;
import com.example.businesslayernew.dto.response.UserResponseDto;
import com.example.businesslayernew.mapper.TicketDtoToTicketEntityMapper;
import com.example.businesslayernew.mapper.TicketToTicketDtoMapper;
import com.example.businesslayernew.mapper.UserDtoToUserMapper;
import com.example.businesslayernew.mapper.UserEntityToUserDtoMapper;
import com.example.businesslayernew.service.TicketService;
import com.example.businesslayernew.service.UserService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Getter
@Setter
@RequestMapping(value = "/api/tickets")
@Api("Tickets controller")
public class TicketController {
    private final TicketService ticketService;
    private final TicketDtoToTicketEntityMapper ticketDtoToTicketEntityMapper;
    private final TicketToTicketDtoMapper ticketToTicketDtoMapper;

    //create new ticket
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create new ticket")
    public ResponseEntity<TicketResponseDto> create(@RequestBody TicketRequestDto request){

        TicketResponseDto ticketResponseDto =
                ticketToTicketDtoMapper.map(ticketService.create(ticketDtoToTicketEntityMapper.map(request)));

        return new ResponseEntity<>( ticketResponseDto, HttpStatus.CREATED);

    }

    //read all tickets
    @GetMapping
    @ApiOperation("read all tickets")
    public List<TicketResponseDto> readAll(){

        return ticketService.readAll().stream().map((ticketToTicketDtoMapper::map)).collect(
                Collectors.toList());

    }

    //read by id
    @GetMapping("{id}")
    @ApiOperation("read ticket by id")
    public ResponseEntity<TicketResponseDto> readById(@PathVariable("id") Long id){
        return new ResponseEntity<>(ticketToTicketDtoMapper.map(ticketService.readById(id)), HttpStatus.OK);
    }

    //update ticket
    @PatchMapping("{id}")
    @ApiOperation("update ticket")
    public ResponseEntity<TicketResponseDto> update(@PathVariable("id") Long id, @RequestBody TicketRequestDto request){
        TicketResponseDto ticketResponseDto = ticketToTicketDtoMapper.map(ticketService.update(id,
                ticketDtoToTicketEntityMapper.map(request)));
        return new ResponseEntity<>(ticketResponseDto, HttpStatus.OK);
    }

    //delete ticket
    @DeleteMapping("{id}")
    @ApiOperation("delete ticket")
    public ResponseEntity<TicketResponseDto> delete(@PathVariable("id") Long id){
        ticketService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
