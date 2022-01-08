package com.anand.license.service;

import com.anand.license.client.FeignClient;
import com.anand.license.client.LoadBalancedClient;
import com.anand.license.client.OrganizationClient;
import com.anand.license.config.ServiceConfig;
import com.anand.license.model.License;
import com.anand.license.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {

    @Autowired
    private ServiceConfig serviceConfig;

    @Autowired
    private OrganizationClient organizationClient;

    @Autowired
    private LoadBalancedClient loadBalancedClient;

    @Autowired
    private FeignClient feignClient;


    public License getLicense(String organizationId,String licenseId,String clientType){
        License license = new License();
        license.setLicenseId(licenseId);
        license.setLicenseType("Licensed");
        license.setDescription("CRM SaaS");
        license.setOrganizationId(organizationId);
        license.setProductName("SalesForce");
        license.setId(1);
        license.setOrganization(retrieveOrganizationInfo(organizationId, clientType));
        return license.withComment(serviceConfig.getProperty());
    }

    private Organization retrieveOrganizationInfo(String organizationId,String clientType){
        switch(clientType){
            case "discoveryClient":
                return organizationClient.getOrganizationByDiscoveryClient(organizationId);

            case "loadBalancedClient":
                return loadBalancedClient.getOrganizationByLoadBalancedClient(organizationId);

            case "feignClient":
                return feignClient.getOrganization(organizationId);

            default:
                return null;
        }
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
