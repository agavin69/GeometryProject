/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometryproject;

import java.util.Comparator;

/**
 *
 * @author antho
 */
public class VolumeSorting implements Comparator<Geom> {
            @Override
            public int compare(Geom o1, Geom o2) {
                return o2.isLargerThan(o1);
            }
        }
     

