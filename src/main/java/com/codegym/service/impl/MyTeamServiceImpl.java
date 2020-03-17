package com.codegym.service.impl;

import com.codegym.model.MyTeam;
import com.codegym.repository.MyTeamRepository;
import com.codegym.service.MyTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class MyTeamServiceImpl implements MyTeamService {
    @Autowired
    private MyTeamRepository myTeamRepository;

    @Override
    public Page<MyTeam> findAll(Pageable pageable) {
        return myTeamRepository.findAll(pageable);
    }

    @Override
    public MyTeam findById(Long id) {
        return myTeamRepository.findById(id).get();
    }

    @Override
    public void save(MyTeam myTeam) {
        myTeamRepository.save(myTeam);
    }

    @Override
    public void remove(Long id) {
        myTeamRepository.deleteById(id);
    }

    @Override
    public Page<MyTeam> findAllByTeamName(String name, Pageable pageable) {
        return myTeamRepository.findAllByTeamName(name, pageable);
    }

    @Override
    public Page<MyTeam> findAllByTeamNumber(String number, Pageable pageable) {
        return myTeamRepository.findAllByTeamNumber(number, pageable);
    }

}
