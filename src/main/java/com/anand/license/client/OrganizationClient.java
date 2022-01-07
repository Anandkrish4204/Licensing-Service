package com.anand.license.client;

import com.anand.license.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OrganizationClient {
    @Autowired
    private DiscoveryClient discoveryClient;


    public Organization getOrganizationByDiscoveryClient(String organizationId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("organization-service");
        if(instances==null)
            return null;
        else
            instances.forEach(instance -> System.out.println(instance.getUri().toString()));
        String url = String.format("%s/v1/organization/%s", instances.get(0).getUri().toString(), organizationId);
        ResponseEntity<Organization> exchange = restTemplate.exchange(url, HttpMethod.GET, null, Organization.class, (Object) null);
        return exchange.getBody();
    }
}
