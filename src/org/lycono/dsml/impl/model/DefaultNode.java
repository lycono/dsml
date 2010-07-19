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
package org.lycono.dsml.impl.model;

import java.util.*;

import org.lycono.dsml.model.*;

/**
 * Default implementation of Node.
 *
 * @author Matt Bateman
 */
public class DefaultNode implements Node {

    private final Map<String, Attribute> mAttrs;
    private final List<Node> mKids;
    private String mName;
    private String mValue;

    public DefaultNode() {
        mAttrs = new HashMap<String, Attribute>();
        mKids = new ArrayList<Node>();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public String getAttributeValue(String name) {
        Attribute attr = mAttrs.get(name);
        return (attr == null) ? null : attr.getValue();
    }

    public Iterator<String> listAttributeNames() {
        return Collections.unmodifiableMap(mAttrs).keySet().iterator();
    }

    public Iterator<Attribute> listAttributes() {
        return Collections.unmodifiableMap(mAttrs).values().iterator();
    }

    public void addAttribute(Attribute attr) {
        mAttrs.put(attr.getName(), attr);
    }

    public Iterator<Node> listChildren() {
        return Collections.unmodifiableList(mKids).iterator();
    }

    public void addChild(Node child) {
        mKids.add(child);
    }

    public boolean hasChildren() {
        return (mKids.size() > 0) ? true : false;
    }

}
