package com.codegym.repository;

import com.codegym.model.MyTeam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface MyTeamRepository extends PagingAndSortingRepository<MyTeam,Long> {
    @Query(value = "select e from MyTeam e where e.name =?1")
    Page<MyTeam> findAllByTeamName(String name, Pageable pageable);

    @Query(value = "select e from MyTeam e where e.number =?1")
    Page<MyTeam> findAllByTeamNumber(String number, Pageable pageable);
}
