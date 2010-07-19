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

import java.io.*;

import org.lycono.dsml.parser.*;
import org.lycono.dsml.impl.parser.state.*;

/**
 * Default implementation of Parser. This implementation simply
 * builds a Document model for the given input.
 *
 * @author Matt Bateman
 */
public class DefaultParser implements Parser {

    private boolean mStateDebug;

    public DefaultParser() {
        mStateDebug = false;
    }

    public void setStateDebug(boolean b) {
        mStateDebug = b;
    }

    public void parse(ParseContext ctx, InputStream in) throws IOException {
        Reader reader = new BufferedReader(new InputStreamReader(in));
        parse(ctx, reader);
    }

    public void parse(ParseContext ctx, Reader in) throws IOException {
        StateContextAdapter sctx = new StateContextAdapter(ctx);
        sctx.setDebug(mStateDebug);
        State s = new StartState();
        while (s != null) {
            s = s.step(sctx, in);
        }
    }

}
