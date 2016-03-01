package com.projects.wens.kandoeteami.organisation.data;

import java.util.List;

/**
 * Created by senne on 22/02/2016.
 */
public class Organisation {
    private int organisationId;
    private String organisationName;
    private String address;
    private String logoUrl;
    private List<Link> links;

    public Organisation() {
        //empty
    }

    //Constructor voor TESTING
    public Organisation(String organisationName, String address){
        this.organisationName = organisationName;
        this.address = address;
    }

    public int getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(int organistionId) {
        this.organisationId = organistionId;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

}
