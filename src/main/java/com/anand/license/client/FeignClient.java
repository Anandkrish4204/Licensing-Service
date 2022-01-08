package com.anand.license.client;


import com.anand.license.model.Organization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.cloud.openfeign.FeignClient("organization-service")
public interface FeignClient {

    @GetMapping("/v1/organization/{organizationId}")
    public Organization getOrganization(@PathVariable String organizationId);
}
