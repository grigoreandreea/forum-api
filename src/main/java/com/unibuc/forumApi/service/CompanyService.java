package com.unibuc.forumApi.service;

import com.unibuc.forumApi.exception.CompanyNotFoundException;
import com.unibuc.forumApi.model.Company;
import com.unibuc.forumApi.model.User;
import com.unibuc.forumApi.repository.CompanyRepository;
import com.unibuc.forumApi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyService {
    private CompanyRepository companyRepository;
    private UserRepository userRepository;

    public CompanyService(
            CompanyRepository companyRepository,
            UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public Optional<List<Company>> getCompanies() {
        return companyRepository.getCompanies();
    }

    public Optional<Company> getCompany(int id) {
        Optional<Company> company = companyRepository.getCompany(id);
        if (company.isEmpty()) {
            throw new CompanyNotFoundException(id);
        }
        Optional<List<User>> employees = userRepository.getUsersByCompany(id);
        if (employees.isPresent()) {
            company.get().setEmployees(employees.get());
        }
        return company;
    }

    public Company create(Company company) {
        return companyRepository.update(company);
    }

    public Company update(Company company) {
        return companyRepository.update(company);
    }

    public void removeCompany(int id) {
        companyRepository.delete(id);
    }
}
