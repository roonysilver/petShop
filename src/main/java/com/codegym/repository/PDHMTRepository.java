package com.codegym.repository;

import com.codegym.model.PetDetailHasMyTeam;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PDHMTRepository extends PagingAndSortingRepository<PetDetailHasMyTeam,Long> {
}
