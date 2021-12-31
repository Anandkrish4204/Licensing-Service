package com.anand.license.service;

import com.anand.license.model.License;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {

    public License getLicense(String organizationId,String licenseId){
        License license = new License();
        license.setLicenseId(licenseId);
        license.setLicenseType("Licensed");
        license.setDescription("CRM SaaS");
        license.setOrganizationId(organizationId);
        license.setProductName("SalesForce");
        license.setId(1);
        return license;
    }


    public String createLicense(String organizationId, License license){
        String responseString = "";
        if(license != null){
            license.setOrganizationId(organizationId);
            responseString = String.format("This is post and the object is %s", license);
        }
        return responseString;
    }

    public String updateLicense(String organizationId, License license){
        String responseString = "";
        if(license != null){
            license.setOrganizationId(organizationId);
            responseString = String.format("This is put and the object is %s", license);
        }
        return responseString;
    }

    public String deleteLicense(String organizationId, String licenseId){
        return String.format("Deleted the license %s for the organization id %s",licenseId,organizationId);
    }
}
