package com.unibuc.forumApi.mapper;

import com.unibuc.forumApi.dto.CompanyRequest;
import com.unibuc.forumApi.model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public Company companyRequestToCompany(CompanyRequest companyRequest) {
        return new Company(
                companyRequest.getName()
        );
    }
}
