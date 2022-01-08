package com.anand.license.client;

import com.anand.license.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LoadBalancedClient {

    @Autowired
    private RestTemplate restTemplate;

    public Organization getOrganizationByLoadBalancedClient(String organizationId){
        return restTemplate
                .exchange("http://organization-service/v1/organization/{organizationId}", HttpMethod.GET,null,Organization.class,organizationId)
                .getBody();
    }
}
