package com.happiness.membread.contexts.study.domain.services;

import com.happiness.membread.common.ApiResponse;
import com.happiness.membread.contexts.study.database.entities.Clazz;
import com.happiness.membread.contexts.study.database.repositories.ClazzRepository;
import com.happiness.membread.contexts.study.domain.dtos.CreateClazzDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class ClazzService {
    @Autowired
    private ClazzRepository clazzRepository;

    public Clazz createClazz(CreateClazzDto clazz){
        Clazz newClazz = new Clazz();
        newClazz.setName(clazz.getName());
        newClazz.setDescription(clazz.getDescription());
        return clazzRepository.save(newClazz);
    }

    public Clazz getClazz(String id){
        return clazzRepository.findById(id).orElseThrow();
    }
}
