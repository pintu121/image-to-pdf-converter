package com.example.imagepdfconverter.controller;

import com.example.imagepdfconverter.dto.ConversionRequest;
import com.example.imagepdfconverter.exception.ImageProcessingException;
import com.example.imagepdfconverter.service.ImagePDFService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ImagePDFController {

    private final ImagePDFService imagePDFService;

    @GetMapping("/")
    public String showUploadForm(Model model) {
        model.addAttribute("conversionRequest", new ConversionRequest());
        return "upload";
    }

    @PostMapping("/convert")
    public String convertToPDF(@ModelAttribute("conversionRequest") ConversionRequest conversionRequest,
                               Model model) {
        MultipartFile image = conversionRequest.getImage();
        int desiredSize = conversionRequest.getDesiredSize();

        try {
            byte[] pdfBytes = imagePDFService.convertImageToPDF(image, desiredSize);
            model.addAttribute("conversionResult", "Conversion successful!");
            model.addAttribute("pdfBytes", pdfBytes);
        } catch (IOException e) {
            model.addAttribute("conversionResult", "Conversion failed. Please try again.");
            e.printStackTrace();
        }

        return "upload";
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadPDF(@ModelAttribute("pdfBytes") byte[] pdfBytes) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=converted.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

    @ExceptionHandler(ImageProcessingException.class)
    public ResponseEntity<String> handleImageProcessingException(ImageProcessingException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image processing error");
    }
}
