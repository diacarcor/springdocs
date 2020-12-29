package com.dccr.springdocs.repositories;

import com.dccr.springdocs.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository <Beer, UUID> {
}
