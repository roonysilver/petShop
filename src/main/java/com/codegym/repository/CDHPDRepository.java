package com.codegym.repository;

import com.codegym.model.CareDetailHasPetDetail;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CDHPDRepository extends PagingAndSortingRepository<CareDetailHasPetDetail,Long> {
}
