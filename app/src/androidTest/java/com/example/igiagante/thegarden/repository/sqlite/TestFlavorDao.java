package com.example.igiagante.thegarden.repository.sqlite;

import android.test.AndroidTestCase;

import com.example.igiagante.thegarden.core.domain.entity.Flavor;
import com.example.igiagante.thegarden.core.repository.sqlite.FlavorDao;

import java.util.ArrayList;

/**
 * @author Ignacio Giagante, on 30/5/16.
 */
public class TestFlavorDao extends AndroidTestCase {

    FlavorDao flavorDao;

    public void deleteAllRecords() {
        TestUtilities.cleanDataBase(mContext);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        deleteAllRecords();
        flavorDao = new FlavorDao(mContext);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        deleteAllRecords();
    }

    public void testAddFlavor(){

        Flavor flavor = TestUtilities.buildFlavor("123");

        long movieId = flavorDao.createFlavor(flavor);

        Flavor flavorFromDb = flavorDao.getFlavor(String.valueOf(movieId));

        assertEquals(flavor.getId(), flavorFromDb.getId());
        assertEquals(flavor.getName(), flavorFromDb.getName());
        assertEquals(flavor.getImageUrl(), flavorFromDb.getImageUrl());
    }

    public void testGetAllFlavors(){

        Flavor flavorOne = TestUtilities.buildFlavor("1");

        flavorDao.createFlavor(flavorOne);

        Flavor flavorTwo = TestUtilities.buildFlavor("2");

        flavorDao.createFlavor(flavorTwo);

        Flavor flavorThree = TestUtilities.buildFlavor("3");

        flavorDao.createFlavor(flavorThree);

        ArrayList<Flavor> flavors = flavorDao.getFlavors();

        assertEquals(flavors.size(), 3);
    }
}