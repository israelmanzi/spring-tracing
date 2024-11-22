package com.automobiles.web;

import com.automobiles.domain.Automobile;

import java.util.Collection;
import java.util.List;

public interface AutomobileResource {

    Automobile saveAutomobile(Automobile automobile);

    Collection<Automobile> getAllAutomobiles();

    Automobile getAutomobileById(Long id);

    Automobile refreshAutomobile(Long id, Automobile automobile);

    String removeAutomobileById(Long id);

    void removeAllAutomobiles();

    List<String> getAllAutomobilesByName();
}
