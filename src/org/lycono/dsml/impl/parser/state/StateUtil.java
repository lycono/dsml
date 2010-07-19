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

/**
 * Utility for state parsing.
 *
 * @author Matt Bateman
 */
public class StateUtil {

    public static final char EOF = 65535;

    public static void errorIfEof(char c) {
        if (c == EOF) {
            throw new RuntimeException("Encountered end of file while parsing.");
        }
    }

    public static boolean isBlankChar(char c) {
        if (c == ' ') {
            return true;
        }
        else if (c == '\n') {
            return true;
        }
        else if (c == '\r') {
            return true;
        }
        else if (c == '\t') {
            return true;
        }
        else {
            return false;
        }
    }

}
