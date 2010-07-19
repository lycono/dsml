/**
   dsml is a simple, terse alternative to XML
   Copyright (C) 2010 Matt Bateman (matt@lycono.com)

   This program is free software; you can redistribute it and/or
   modify it under the terms of the GNU General Public License
   as published by the Free Software Foundation; either version 2
   of the License, or (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/
package org.lycono.dsml.model;

import java.util.*;

/**
 * Represents a dsml node.
 *
 * @author Matt Bateman
 */
public interface Node {

    public String getName();

    public String getValue();

    public String getAttributeValue(String name);

    public Iterator<String> listAttributeNames();

    public Iterator<Attribute> listAttributes();

    public Iterator<Node> listChildren();

    public boolean hasChildren();

}
