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
import org.lycono.dsml.impl.parser.state.*;
import org.lycono.dsml.impl.model.*;

/**
 * Adapts a ParseContext implementation to a StateContext implementation.
 * This translates StateEvents into Parse events.
 *
 * @author Matt Bateman
 */
public class StateContextAdapter implements StateContext {

    private final ParseContext mCtx;
    private final Stack<String> mTagNames;
    private String mAttrName;
    private boolean mDebug;
    private int mLineNum;
    private int mCharNum;

    public StateContextAdapter(ParseContext ctx) {
        mCtx = ctx;
        mTagNames = new Stack<String>();
        mDebug = false;
        mLineNum = 1;
        mCharNum = 1;
    }

    public void setDebug(boolean b) {
        mDebug = b;
    }

    public void onChar(char c) {
        if (c == '\n') {
            mLineNum++;
            mCharNum = 1;
        }
        else {
            mCharNum++;
        }
    }

    public int getLineNumber() {
        return mLineNum;
    }

    public int getCharNumber() {
        return mCharNum;
    }

    public void onEvent(StateEvent e) {
        if (mDebug) {
            System.out.println(e);
        }
        if (e.getType() == StateEvent.Type.TAG_NAME) {
            mCtx.onNodeStart(e.getValue());
            mTagNames.push(e.getValue());
        }
        else if (e.getType() == StateEvent.Type.TAG_VALUE) {
            mCtx.onNodeValue(e.getValue());
        }
        else if (e.getType() == StateEvent.Type.ATTR_NAME) {
            mAttrName = e.getValue();
        }
        else if (e.getType() == StateEvent.Type.ATTR_VALUE) {
            mCtx.onAttribute(mAttrName, e.getValue());
            mAttrName = null;
        }
        else if (e.getType() == StateEvent.Type.END_TAG) {
            mCtx.onNodeEnd(mTagNames.peek());
            mTagNames.pop();
        }
    }

    public String getCurrentTagName() {
        return mTagNames.peek();
    }

}
