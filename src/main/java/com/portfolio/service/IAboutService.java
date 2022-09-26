package com.portfolio.service;

import com.portfolio.dto.AboutRequest;
import com.portfolio.dto.AboutResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IAboutService {

    AboutResponse getAbout();
    AboutResponse createAbout(AboutRequest aboutRequest);
    AboutResponse updateAbout(Long id, MultipartFile file, AboutRequest aboutRequest);

}
