package com.ironhack.MidtermProject.service.impl;

import com.ironhack.MidtermProject.dao.additional.Address;
import com.ironhack.MidtermProject.repository.additional.AddressRepository;
import com.ironhack.MidtermProject.service.interfaces.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AddressService implements IAddressService {

    @Autowired
    AddressRepository addressRepository;

    public Address createAddress(Address address){
        Optional<Address> adrs = addressRepository.findByCityAndStreetAndNumberAndPostCode(address.getCity(), address.getStreet(), address.getNumber(), address.getPostCode());
        long adrsId = adrs.get().getAddressId();
        if(adrs.isEmpty()){
            Address newAddress = new Address(address.getCity(), address.getStreet(), address.getNumber(), address.getPostCode());
            return addressRepository.save(newAddress);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "This Address already exists in the system. Its id is: " + adrsId);
        }
    }
}
