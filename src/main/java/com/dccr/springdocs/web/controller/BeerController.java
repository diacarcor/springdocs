package com.dccr.springdocs.web.controller;

import com.dccr.springdocs.domain.Beer;
import com.dccr.springdocs.repositories.BeerRepository;
import com.dccr.springdocs.web.mappers.BeerMapper;
import com.dccr.springdocs.web.model.BeerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping ("/api/v1/beer/")
public class BeerController {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    public BeerController(BeerRepository beerRepository, BeerMapper beerMapper) {
        this.beerRepository = beerRepository;
        this.beerMapper = beerMapper;
    }


    @GetMapping("{id}")
    @ResponseStatus (HttpStatus.OK)
    public BeerDTO getBeerById(@PathVariable String id){

        Beer consultedBeer = beerRepository.findById(UUID.fromString(id)).get();
        BeerDTO returnedBeerDTO = beerMapper.beerToBeerDto(consultedBeer);
        return returnedBeerDTO ;

    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public BeerDTO newBeer(@RequestBody  @Validated BeerDTO beerDTO){

        Beer newBeer = beerMapper.beerDTOToBeer(beerDTO);
        BeerDTO returnedBeerDTO = beerMapper.beerToBeerDto(beerRepository.save(newBeer));
        return returnedBeerDTO ;

    }

    @PutMapping ("{id}")
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateBeer (@PathVariable String id, @RequestBody  @Validated BeerDTO beerDTO){
        beerRepository.findById(UUID.fromString(id)).ifPresent(beer -> {
            beer.setUpc(beerDTO.getUpc());
            beer.setPrice(beerDTO.getPrice());
            beer.setBeerStyle(beerDTO.getBeerStyle().name());
            beer.setBeerName(beerDTO.getBeerName());

            beerRepository.save(beer);
        });


        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
