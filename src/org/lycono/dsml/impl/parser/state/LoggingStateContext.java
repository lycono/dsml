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
package org.lycono.dsml.impl.parser.state;

import java.util.Stack;

/**
 * Parsing state.
 *
 * @author Matt Bateman
 */
public class LoggingStateContext implements StateContext {

    private Stack<String> mTagNames;

    public LoggingStateContext() {
        mTagNames = new Stack<String>();
    }

    public void onEvent(StateEvent e) {
        if (e.getType() == StateEvent.Type.TAG_NAME) {
            mTagNames.push(e.getValue());
        }
        else if (e.getType() == StateEvent.Type.END_TAG) {
            mTagNames.pop();
        }
        System.out.println(e);
    }

    public String getCurrentTagName() {
        return mTagNames.peek();
    }

}
