package com.portfolio.service.impl;


import com.portfolio.dto.TechnologyRequest;
import com.portfolio.dto.TechnologyResponse;
import com.portfolio.entity.Technology;
import com.portfolio.mapper.TechnologyMapperImpl;
import com.portfolio.repository.TechnologyRepository;
import com.portfolio.service.IFileService;
import com.portfolio.service.ITechnologyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TechnologyServiceImpl implements ITechnologyService {

    private final TechnologyRepository technologyRepository;
    private final TechnologyMapperImpl technologyMapper;
    private final IFileService fileService;

    @Override
    public List<TechnologyResponse> getAllTechnologies() {
        return technologyMapper.toDtoList(technologyRepository.findAll());
    }

    @Override
    public TechnologyResponse getTechnology(Long id) {
        return technologyMapper.toDto(
                technologyRepository.findById(id).orElseThrow(() -> new RuntimeException("Technology not found."))
        );
    }

    @Override
    public TechnologyResponse createTechnology(MultipartFile file, TechnologyRequest technology) {
        technology.setImage(fileService.saveFile(file).toString());
        return technologyMapper.toDto(technologyRepository.save(technologyMapper.toEntity(technology)));
    }

    @Override
    public TechnologyResponse updateTechnology(Long id, MultipartFile file, TechnologyRequest technology) {
        Technology entity = technologyRepository.findById(id).orElseThrow(() -> new RuntimeException("Technology not found."));
        if (entity.getImage() != null) {
            fileService.deleteFile(entity.getImage());
        }

        technology.setImage(fileService.saveFile(file).toString());
        return technologyMapper.toDto(technologyRepository.save(technologyMapper.toEntity(entity, technology)));
    }

    @Override
    public void deleteTechnology(Long id) {
        if (!technologyRepository.existsById(id)) {
            throw new RuntimeException("Technology not found.");
        }
        Optional<Technology> technology = technologyRepository.findById(id);
        if (technology.isPresent() && technology.get().getImage() != null) fileService.deleteFile(technology.get().getImage());
        technologyRepository.deleteById(id);
    }
}
