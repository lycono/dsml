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

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.lycono.dsml.model.*;

/**
 * Default implementation of Document.
 *
 * @author Matt Bateman
 */
public class DefaultDocument implements Document {

    private final List<Node> mRoots;

    public DefaultDocument() {
        mRoots = new ArrayList<Node>();
    }

    public DefaultDocument(List<Node> roots) {
        mRoots = new ArrayList<Node>(roots);
    }

    public int getRootCount() {
        return mRoots.size();
    }

    public Iterator<Node> listRoots() {
        return Collections.unmodifiableList(mRoots).iterator();
    }

    public Node getRoot(int pos) {
        return mRoots.get(pos);
    }

}
