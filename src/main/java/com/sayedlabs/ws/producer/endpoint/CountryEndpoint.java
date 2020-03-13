package com.sayedlabs.ws.producer.endpoint;

import com.sayedlabs.ws.producer.Country;
import com.sayedlabs.ws.producer.GetCountryRequest;
import com.sayedlabs.ws.producer.GetCountryResponse;
import com.sayedlabs.ws.producer.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://sayedlabs.com/ws/producer";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request){
        GetCountryResponse getCountryResponse = new GetCountryResponse();
        Country country = countryRepository.findCountry(request.getName());
        getCountryResponse.setCountry(country);
        return getCountryResponse;
    }
}
