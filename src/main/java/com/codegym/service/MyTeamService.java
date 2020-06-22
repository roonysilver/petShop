package com.codegym.service;


import com.codegym.model.MyTeam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MyTeamService {
    Page<MyTeam> findAll(Pageable pageable);

    MyTeam findById(Long id);

    void save(MyTeam myTeam);

    void remove(Long id);

    Page<MyTeam> findAllByTeamName(String name, Pageable pageable);

}
