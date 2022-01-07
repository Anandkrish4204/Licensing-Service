package com.anand.license.controller;

import com.anand.license.model.License;
import com.anand.license.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/organization/{organizationId}/license")
public class LicenseController {

    @Autowired
    LicenseService licenseService;

    @GetMapping("/{licenseId}/{clientType}")
    public ResponseEntity<License> getLicense(@PathVariable String organizationId,@PathVariable String licenseId,@PathVariable String clientType){
        License license = licenseService.getLicense(organizationId, licenseId, clientType);
        license.add(
                linkTo(methodOn(LicenseController.class).getLicense(organizationId,licenseId,clientType)).withSelfRel(),
                linkTo(methodOn(LicenseController.class).createLicense(organizationId,license)).withRel("createLicense"),
                linkTo(methodOn(LicenseController.class).updateLicense(organizationId,license)).withRel("updateLicence"),
                linkTo(methodOn(LicenseController.class).deleteLicense(organizationId,licenseId)).withRel("deleteLicense")
        );
        return ResponseEntity.ok(license);
    }

    @PostMapping
    public ResponseEntity<String> createLicense(@PathVariable String organizationId, @RequestBody License license){
        return ResponseEntity.ok(licenseService.createLicense(organizationId,license));
    }

    @PutMapping
    public ResponseEntity<String> updateLicense(@PathVariable String organizationId,@RequestBody License license){
        return ResponseEntity.ok(licenseService.updateLicense(organizationId,license));
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable String organizationId,@PathVariable String licenseId){
        return ResponseEntity.ok(licenseService.deleteLicense(organizationId,licenseId));
    }
}
