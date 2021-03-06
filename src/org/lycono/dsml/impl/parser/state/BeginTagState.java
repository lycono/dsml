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

import java.io.*;

/**
 * Parsing state.
 *
 * @author Matt Bateman
 */
public class BeginTagState implements State {

    public BeginTagState() {}

    public State step(StateContext ctx, Reader in) throws IOException, StateException {
        ctx.onEvent(new StateEvent(StateEvent.Type.BEGIN_TAG));
        char c = (char) in.read();
        if (StateUtil.isBlankChar(c)) {
            return this;
        }
        else if (Character.isLetter(c)) {
            TagNameState next = new TagNameState();
            next.append(c);
            return next;
        }
        else {
            throw StateUtil.createCharError(ctx, c, new String[] {"blank", "letter"});
        }
    }

}
