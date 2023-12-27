package com.example.Employee.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {
	
	
	@GetMapping(value = "/generate/{text}", produces = MediaType.IMAGE_PNG_VALUE)
    public void generateQRCode(@PathVariable String text, HttpServletResponse response) throws WriterException, IOException {
        int width = 200;
        int height = 200;

        // Create a QRCodeWriter
        Writer writer = new QRCodeWriter();

        // Generate the QR code bit matrix
        BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);

        // Convert the bit matrix to a BufferedImage
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        // Write the image to the response
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
        outputStream.close();
    }


}
