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
package org.lycono.dsml.impl.parser;

import java.util.*;

import org.lycono.dsml.parser.*;
import org.lycono.dsml.impl.model.*;
import org.lycono.dsml.model.*;

/**
 * ParseContext implementation that simply builds a Document model
 * for the given input.
 *
 * @author Matt Bateman
 */
public class ModelBuildingContext implements ParseContext {

    private final Stack<DefaultNode> mNodes;
    private List<Node> mRoots;
    private boolean mDebug;

    public ModelBuildingContext() {
        mNodes = new Stack<DefaultNode>();
        mRoots = new ArrayList<Node>();
        mDebug = false;
    }

    public void setDebug(boolean b) {
        mDebug = b;
    }

    public void onNodeStart(String name) {
        if (mDebug) {
            System.out.println("Node Start: " + name);
        }
        DefaultNode node = new DefaultNode();
        node.setName(name);
        if (mNodes.isEmpty()) {
            //-- This is a root node.
            mRoots.add(node);
        }
        else {
            mNodes.peek().addChild(node);
        }
        mNodes.push(node);
    }

    public void onNodeEnd(String name) {
        if (mDebug) {
            System.out.println("Node End: " + name);
        }
        mNodes.pop();
    }

    public void onNodeValue(String value) {
        if (mDebug) {
            System.out.println("Node Value: " + value);
        }
        mNodes.peek().setValue(value);
    }

    public void onAttribute(String name, String value) {
        if (mDebug) {
            System.out.println("Attribute: " + name + "=" + value);
        }
        mNodes.peek().addAttribute(new DefaultAttribute(name, value));
    }

    public Document getDocument() {
        return new DefaultDocument(mRoots);
    }

}
