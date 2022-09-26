package com.portfolio.service.impl;

import com.portfolio.dto.AboutRequest;
import com.portfolio.dto.AboutResponse;
import com.portfolio.entity.About;
import com.portfolio.mapper.AboutMapperImpl;
import com.portfolio.repository.AboutRepository;
import com.portfolio.service.IAboutService;
import com.portfolio.service.IFileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Service
public class AboutServiceImpl implements IAboutService {

    private final AboutRepository aboutRepository;
    private final AboutMapperImpl aboutMapper;
    private final IFileService fileService;

    @Override
    public AboutResponse getAbout() {
        return aboutMapper.toDto(aboutRepository.findFirstByOrderById()
                .orElseThrow(() -> new RuntimeException("About not found.")));
    }

    @Override
    public AboutResponse createAbout(AboutRequest aboutRequest) {
        if (aboutRepository.findAll().size() > 0) throw new RuntimeException("About already exists.");
        return aboutMapper.toDto(aboutRepository.save(aboutMapper.toEntity(aboutRequest)));
    }

    @Override
    public AboutResponse updateAbout(Long id, MultipartFile file, AboutRequest aboutRequest) {
        About entity = aboutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("About not found."));

        String image = entity.getImage();
        String filename = fileService.saveFile(file).toString();
        if (image != null) {
            fileService.deleteFile(image);
        }
        entity.setImage(filename);

        return aboutMapper.toDto(aboutRepository.save(aboutMapper.toEntity(entity, aboutRequest)));
    }
}
