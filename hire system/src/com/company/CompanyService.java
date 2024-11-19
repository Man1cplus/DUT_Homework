package com.company;

import java.util.List;

public class CompanyService {
    private final CompanyRepository repository;
    public CompanyService(CompanyRepository repository){
        this.repository=repository;
    }
    public List<Company> getAllCompanies(){
        return repository.findAll();
    }
    public void addCompany(Company company){

        repository.save(company);
    }

}
