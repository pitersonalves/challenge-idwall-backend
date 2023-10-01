package com.wanted.idwall.service;

import com.wanted.idwall.model.WantedClassification;
import com.wanted.idwall.repository.WantedClassificationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassificationService {

    private final WantedClassificationRepository wantedClassificationRepository;


    public WantedClassification create(WantedClassification request) {
        return wantedClassificationRepository.save(request);
    }

    public List<String> findAllClassificationName(){
        return wantedClassificationRepository.findAllClassificationName();
    }

    public List<WantedClassification> findByPersonWantedId(Integer id){
        return wantedClassificationRepository.findByPersonWantedId(id);
    }
}
