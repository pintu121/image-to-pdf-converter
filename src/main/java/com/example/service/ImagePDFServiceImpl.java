package com.example.imagepdfconverter.service;

import com.example.imagepdfconverter.exception.ImageProcessingException;
import com.example.imagepdfconverter.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImagePDFServiceImpl implements ImagePDFService {

    private final ImageUtil imageUtil;

    @Override
    public byte[] convertImageToPDF(MultipartFile image, int desiredSize) throws IOException {
        // Implement your image to PDF conversion logic here
        // Use the ImageUtil class for image-related operations

        // Simulate an image processing exception for demonstration purposes
        if (desiredSize < 0) {
            throw new ImageProcessingException("Invalid desired size");
        }

        // Replace this with actual logic to convert image to PDF
        // For simplicity, return a dummy byte array
        return new byte[0];
    }
}
