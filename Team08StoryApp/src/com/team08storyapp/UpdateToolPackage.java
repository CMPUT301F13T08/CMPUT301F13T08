/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  ���  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
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

import android.content.Context;

/**
 * UpdateToolPackage is a class that mainly works as a parameter object. Since
 * sometimes a function requires both context, FileHelper, ESHelper and other
 * objects, it may be too much for a function to have 4 and more parameters.
 * Thus, a parameter object named UpdateToolPackage is created. Because this
 * parameter object is required only by functions that related to updating a
 * story to web server at the most of time. So the it's named as
 * "UpdateTookPackage".
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 * 
 */
public class UpdateToolPackage {

    private FileHelper fHelper;
    private Context context;
    private ESHelper esHelper;

    /**
     * Constructor of UpdateToolPackage requires a FileHelper object, ESHelper
     * Object, Context object. And assign them to the FileHelper, ESHelper,
     * Context object in UpdateToolPackage respectively.
     * 
     * @param fHelper
     *            Simply a FileHelper object
     * @param esHelper
     *            an ESHelper object
     * @param context
     *            an android Context object
     */
    public UpdateToolPackage(FileHelper fHelper, ESHelper esHelper,
	    Context context) {
	this.fHelper = fHelper;
	this.context = context;
	this.esHelper = esHelper;
    }

    /**
     * This function is called when the FileHelper of UpdateTookPackage is in
     * need.
     * 
     * @return FileHelper, the FileHelper field in an UpdateToolPackage object.
     */
    public FileHelper getfHelper() {
	return fHelper;
    }

    /**
     * This function is called when the ESHelper of UpdateTookPackage is in
     * need.
     * 
     * @return FileHelper, the ESHelper field in an UpdateToolPackage object.
     */
    public ESHelper getESHelper() {
	return esHelper;
    }
 
    /**
     * This function is called when the Context of UpdateTookPackage is in
     * need.
     * 
     * @return FileHelper, the Context field in an UpdateToolPackage object.
     */
    public Context getContext() {
	return context;
    }

}
