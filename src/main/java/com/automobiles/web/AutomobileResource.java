package com.automobiles.web;

import com.automobiles.domain.Automobile;

import java.util.Collection;
import java.util.List;

public interface AutomobileResource {

    Automobile saveAutomobile(Automobile automobile);

    Collection<Automobile> getAllAutomobiles();

    Automobile getAutomobileById(Long id);
}
