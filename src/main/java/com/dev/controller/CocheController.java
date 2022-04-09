package com.dev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dev.model.Coche;
import com.dev.model.Person;
import com.dev.repository.CocheRepository;
import com.dev.service.CocheService;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpEntity;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;









import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xhtmlrenderer.pdf.ITextRenderer;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class CocheController {

	@Autowired
	private CocheRepository CocheRepository;

	@Autowired
	private CocheService CocheService;
	@GetMapping("/dataPrueba")
	public List<Coche> getDataPrueba() {
		return CocheService.lista();
	}

	/*@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}*/

	@RequestMapping(value = "/template/products")
	public  Object[]  getProductList() {
		Person[] arrayDat=CocheService.listaApi();
		System.out.println(arrayDat[0].email);
	   return arrayDat;
	}
	
    private static final SimpleDateFormat filenameDate = new SimpleDateFormat("ddMMyyyyHHmmss");
    private static final SimpleDateFormat readableDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    @RequestMapping(value = "/export", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getExport() {
        ITextRenderer renderer = new ITextRenderer();
        ByteArrayOutputStream boas = null;
        

        try {
        
                            // com/dev/controller
            String inputFile = "src/main/resources/templates/index.html";
            String outputFile = "src/main/resources/templates/index_"+filenameDate.format(new Date())+".pdf";

            String html = new String(Files.readAllBytes(Paths.get(inputFile)));
            final Document document = Jsoup.parse(html);
            document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
            document.body().select(".DOC_GENERATED_DATE").html(readableDate.format(new Date()));

            renderer.setDocumentFromString(document.html());
            renderer.layout();

            try (OutputStream os = Files.newOutputStream(Paths.get(outputFile))) {
                renderer.createPDF(os);
                os.close();

                PdfReader reader = new PdfReader(outputFile);
                boas = new ByteArrayOutputStream();
                PdfStamper stamper = new PdfStamper(reader, boas);
                stamper.setPageAction(PdfWriter.PAGE_OPEN, new PdfAction(PdfAction.PRINTDIALOG), 1); 
                stamper.close();
            } catch (DocumentException ex) {
                Logger.getLogger(CocheController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   catch (IOException ex) {
            Logger.getLogger(CocheController.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(boas.toByteArray(), headers, HttpStatus.OK);
      

        return response;
    }
	
}
