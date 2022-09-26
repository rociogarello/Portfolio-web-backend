package com.portfolio.service;

import com.portfolio.dto.TechnologyRequest;
import com.portfolio.dto.TechnologyResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ITechnologyService {

    List<TechnologyResponse> getAllTechnologies();
    TechnologyResponse getTechnology(Long id);
    TechnologyResponse createTechnology(MultipartFile file, TechnologyRequest technology);
    TechnologyResponse updateTechnology(Long id, MultipartFile file, TechnologyRequest technology);
    void deleteTechnology(Long id);

}
