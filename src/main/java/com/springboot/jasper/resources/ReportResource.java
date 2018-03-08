package com.springboot.jasper.resources;

import com.springboot.jasper.enums.ExportReportType;
import com.springboot.jasper.model.Report;
import com.springboot.jasper.services.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Description;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Description(value = "Jasper Report Resource Handler")
@RestController
@RequestMapping("/api/report")
public class ReportResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportResource.class);

    private ReportService reportService;

    /**
     * Constructor dependency injector.
     * @param reportService - report service dependency
     */
    public ReportResource(ReportService reportService) {
        this.reportService = reportService;
    }


    /**
     * Endpoint for generating simple PDF report
     *
     * @param report - report data
     * @return byte array resource (generated file in bytes)
     */
    @PostMapping(value = "/pdf", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ByteArrayResource> generateSimplePDFReport(@Valid @RequestBody Report report)
    {
        LOGGER.info("Payload for generating simple PDF report: {}", report);
        try
        {
            ByteArrayResource byteArrayResource = reportService.generateSimpleReport(report, ExportReportType.PDF);
            return new ResponseEntity<>(byteArrayResource, HttpStatus.OK);
        }
        catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint for generating simple DOCx report
     *
     * @param report - report data
     * @return byte array resource (generated file in bytes)
     */
    @PostMapping(value = "/docx", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ByteArrayResource> generateSimpleDOCxReport(@Valid @RequestBody Report report)
    {
        LOGGER.info("Payload for generating simple DOCx report: {}", report);
        try
        {
            ByteArrayResource byteArrayResource = reportService.generateSimpleReport(report, ExportReportType.DOCX);
            return new ResponseEntity<>(byteArrayResource, HttpStatus.OK);
        }
        catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint for generating report with table data source
     *
     * @param reportDataSource - report data source
     * @return byte array resource (generated file in bytes)
     */
    @PostMapping(value = "/data-source", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ByteArrayResource> generateDataSourceReport(@Valid @RequestBody List<Report> reportDataSource)
    {
        LOGGER.info("Payload for generating report with data source: {}", reportDataSource);
        try
        {
            ByteArrayResource byteArrayResource = reportService.generateDataSourceReport(
                    reportDataSource, ExportReportType.PDF
            );
            return new ResponseEntity<>(byteArrayResource, HttpStatus.OK);
        }
        catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
