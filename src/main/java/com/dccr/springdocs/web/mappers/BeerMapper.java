package com.dccr.springdocs.web.mappers;

import com.dccr.springdocs.domain.Beer;
import com.dccr.springdocs.web.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDTO beerToBeerDto (Beer beer);
    Beer beerDTOToBeer (BeerDTO beerDTO);
}
