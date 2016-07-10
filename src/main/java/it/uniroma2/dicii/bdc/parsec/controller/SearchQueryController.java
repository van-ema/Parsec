package it.uniroma2.dicii.bdc.parsec.controller;


import it.uniroma2.dicii.bdc.parsec.model.Flux;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.Luminosity;
import it.uniroma2.dicii.bdc.parsec.model.Metallicity;
import it.uniroma2.dicii.bdc.parsec.model.dao.FluxDAO;
import it.uniroma2.dicii.bdc.parsec.model.dao.GalaxyDAO;
import it.uniroma2.dicii.bdc.parsec.model.dao.LuminosityDAO;
import it.uniroma2.dicii.bdc.parsec.model.dao.MetallicityDAO;
import it.uniroma2.dicii.bdc.parsec.view.QueryBoundary;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class SearchQueryController {

    public SearchQueryController() {
    }

    /**
     * Query to visualize position, distance and redshift value, luminosity, metallicity
     * and relative errors
     *
     * @param galaxyName name of galaxy to search info about
     * @return {@link Galaxy}
     */
    public Galaxy searchGalaxyByName(String galaxyName) {

        try {
            return GalaxyDAO.findByName(galaxyName);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Metallicity> searchMetallicityByGalaxy(String galaxyName) {

        try {
            return MetallicityDAO.findByGalaxy(new Galaxy(galaxyName));

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Luminosity> searchLuminosityByGalaxy(String galaxyName) {

        try {
            return LuminosityDAO.findByGalaxy(new Galaxy(galaxyName));

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<String> composeLinesList(QueryBoundary query) {

        List<java.lang.String> l = new ArrayList<java.lang.String>();

        if ( query.isAtomOIV259() != null && query.isAtomOIV259() ) {
            l.add("OIV25.9");
        }
        if ( query.isAtomNEV143() != null && query.isAtomNEV143() ) {
            l.add("NEV14.3");
        }
        if ( query.isAtomNEV243() != null && query.isAtomNEV243() ) {
            l.add("NEV24.3");
        }
        if ( query.isAtomOIII52() != null && query.isAtomOIII52() ) {
            l.add("OIII52");
        }
        if ( query.isAtomNIII57() != null && query.isAtomNIII57() ) {
            l.add("NIII57");
        }
        if ( query.isAtomOI63() != null && query.isAtomOI63() ) {
            l.add("OI63");
        }
        if ( query.isAtomOIII88() != null && query.isAtomOIII88() ) {
            l.add("OIII88");
        }
        if ( query.isAtomNII122() != null && query.isAtomNII122() ) {
            l.add("NII122");
        }
        if ( query.isAtomOI145() != null && query.isAtomOI145() ) {
            l.add("OI145");
        }
        if ( query.isAtomCII158() != null && query.isAtomCII158() ) {
            l.add("CII158");
        }
        if ( query.isAtomSIV105() != null && query.isAtomSIV105() ) {
            l.add("SIV10.5");
        }
        if ( query.isAtomNEII128() != null && query.isAtomNEII128() ) {
            l.add("NEII12.8");
        }
        if ( query.isAtomNEIII156() != null && query.isAtomNEIII156() ) {
            l.add("NEIII15.6");
        }
        if ( query.isAtomSIII187() != null && query.isAtomSIII187() ) {
            l.add("SIII18.7");
        }
        if ( query.isAtomSIII335() != null && query.isAtomSIII335() ) {
            l.add("SIII33.5");
        }
        if ( query.isAtomSII348() != null && query.isAtomSII348() ) {
            l.add("SII34.8");
        }
        return l;
    }

    public List<Flux> searchGalaxySpectralLines(QueryBoundary query) {

        Galaxy galaxy = new Galaxy(query.getGalaxyName());
        List<String> lines = composeLinesList(query);
        try {
            return FluxDAO.findLinesByGalaxy(galaxy, lines);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Flux> searchAllGalaxySpectralLines(QueryBoundary query) {

        Galaxy galaxy = new Galaxy(query.getGalaxyName());
        try {
            return FluxDAO.findAllLinesByGalaxy(galaxy);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Flux> searchGalaxySpectralLinesForRatio(QueryBoundary query) {

        Galaxy galaxy = new Galaxy(query.getGalaxyName());
        List<String> lines = new ArrayList<String>();
        lines.add(query.getFluxNum());
        lines.add(query.getFluxDen());
        try {
            return FluxDAO.findLinesByGalaxy(galaxy, lines);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

}