package com.sadk.algorithm.editDistance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author zhangchong
 * @CodeReviewer
 * @Description
 */
public class EditDistanceTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void distance() {
        System.out.println(EditDistance.distance("SNOWY", "SUNNY"));
    }


    @Test
    public void distance1() {
        System.out.println(EditDistance.distance1("SNOWY", "SUNNY"));
    }

    @Test
    public void diffSet() {

    }

}