package com.vasu.api;

import com.vasu.configuration.AuthorityPropertyEditor;
import com.vasu.configuration.SplitCollectionEditor;
import com.vasu.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;


@Controller
@RequestMapping("clients")
public class ClientsController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private JdbcClientDetailsService clientsDetailsService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Collection.class,new SplitCollectionEditor(Set.class,","));
        binder.registerCustomEditor(GrantedAuthority.class,new AuthorityPropertyEditor());

    }


    @RequestMapping(value="/form",method= RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_OAUTH_ADMIN')")
    public String showEditForm(@RequestParam(value="client",required=false)String clientId, Model model){
        model.addAttribute("clientDetails",clientService.fetchClientDetails(clientId));
        return "form";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_OAUTH_ADMIN')")
    public String editClient(
            @ModelAttribute BaseClientDetails clientDetails,
            @RequestParam(value = "newClient", required = false) String newClient
    ) {
        clientService.editClient(clientDetails, newClient);
        return "redirect:/";
    }

    @RequestMapping(value="{client.clientId}/delete",method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_OAUTH_ADMIN')")
    public String deleteClient(@ModelAttribute BaseClientDetails clientDetails,@PathVariable("client.clientId") String id){
        clientService.deleteClient(clientDetails, id);
        return "redirect:/";
    }
}
