package com.vasu.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientService {
    @Autowired
    private JdbcClientDetailsService clientsDetailsService;


    public ClientDetails fetchClientDetails(String clientId) {
        ClientDetails clientDetails = null;
        try {
            if (clientId != null) {
                clientDetails = clientsDetailsService.loadClientByClientId(clientId);
            } else {
                clientDetails = new BaseClientDetails();
            }
        } catch (InvalidClientException ex) {
            log.error(ex.toString());
        }

        return clientDetails;
    }


    public void editClient(BaseClientDetails clientDetails, String newClient) {
        try{
            if (newClient == null) {
                clientsDetailsService.updateClientDetails(clientDetails);
            } else {
                clientsDetailsService.addClientDetails(clientDetails);
            }
            if (!clientDetails.getClientSecret().isEmpty()) {
                clientsDetailsService.updateClientSecret(clientDetails.getClientId(), clientDetails.getClientSecret());
            }
        } catch(NoSuchClientException ex) {
            log.error(ex.toString());
        }

    }

    public void deleteClient(BaseClientDetails clientDetails, String clientId) {
        try{
            clientsDetailsService.removeClientDetails(clientsDetailsService.loadClientByClientId(clientId).toString());
        } catch(NoSuchClientException ex) {
            log.error(ex.toString());
        }

    }


}
