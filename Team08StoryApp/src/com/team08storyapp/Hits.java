/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  ©  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
Free Software Foundation, Inc., Marky Mark  License GPLv3+: GNU
GPL version 3 or later <http://gnu.org/licenses/gpl.html>.
This program is free software: you can redistribute it and/or modify it under the terms of 
the GNU General Public License as published by the Free Software Foundation, either 
version 3 of the License, or (at your option) any later version. This program is distributed 
in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 
warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public 
License for more details. You should have received a copy of the GNU General Public License 
along with this program.  If not, see <http://www.gnu.org/licenses/>.
              
3rd Party Libraries
=============
Retrieved Oct. 27, 2013 - https://github.com/rayzhangcl/ESDemo
-This demo was used to help with JSON and ESHelper which is under the CC0 licenses

Retrieved Oct. 29, 2013  - http://hc.apache.org/downloads.cgi
-This is for the fluent library which is licensed under apache V2

Retrieved Oct. 29, 2013 
- https://code.google.com/p/google-gson/downloads/detail?name=google-gson-2.2.4-release.zip&can=2&q=
-This is for JSON which is licensed under apache V2
 */

package com.team08storyapp;

import java.util.Collection;

/**
 * Hits is a model class used in conjunction with the ElasticSearch. It helps
 * store the number of hits a call to the webservice using ElasticSearch
 * returns.
 * <p>
 * This class is a strongly typed class and is used on model classes
 * representing the JSON objects to return from an ElasticSearch.
 * 
 * @param <T>
 *            The java object that will be returned via JSON from the
 *            ElasticSearch.
 * 
 * @see ESHelper
 * 
 * @author Abram Hindle and Chenlei Zhang (@link
 *         https://github.com/rayzhangcl/ESDemo)
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0 *
 */
public class Hits<T> {
    int total;
    double max_score;
    Collection<ElasticSearchResponse<T>> hits;

    /**
     * @return The number of hits a search has retrieved.
     */
    public Collection<ElasticSearchResponse<T>> getHits() {
	return hits;
    }

    /**
     * This method overrides the toString() method. Allowing it to display the
     * properties of this model class when the toString method is called.
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
	return (super.toString() + "," + total + "," + max_score + "," + hits);
    }
}
