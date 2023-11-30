package com.example.imagepdfconverter.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ConversionRequest {
    private MultipartFile image;
    private int desiredSize;
}
