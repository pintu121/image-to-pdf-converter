package com.example.imagepdfconverter.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImagePDFService {
    byte[] convertImageToPDF(MultipartFile image, int desiredSize) throws IOException;
}
