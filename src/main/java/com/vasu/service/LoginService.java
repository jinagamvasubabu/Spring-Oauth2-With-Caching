package com.vasu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Service
public class LoginService {
    @Autowired
    private JdbcClientDetailsService clientDetailsService;
    @Autowired
    private ApprovalStore approvalStore;
    @Autowired
    private TokenStore tokenStore;

    public List<Approval> getApprovals(Map<String, Object> model, Principal principal) {
        return clientDetailsService.listClientDetails().stream()
                .map(clientDetails -> approvalStore.getApprovals(principal.getName(), clientDetails.getClientId()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public void revokeApproval(Approval approval) {
        approvalStore.revokeApprovals(asList(approval));
        tokenStore.findTokensByClientIdAndUserName(approval.getClientId(), approval.getUserId())
                .forEach(tokenStore::removeAccessToken);
    }
}
