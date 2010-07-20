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
package org.lycono.dsml.parser;

/**
 * Exception for invalid content found while parsing.
 *
 * @author Matt Bateman
 */
public class InvalidContentException extends ParseException {

    private final int mLineNum;
    private final int mCharNum;
    private final String[] mValidChars;
    private final char mInvalidChar;

    public InvalidContentException(int lineNum, int charNum, String[] validChars, char invalidChar) {
        super();
        mLineNum = lineNum;
        mCharNum = charNum;
        mValidChars = validChars;
        mInvalidChar = invalidChar;
    }

    public InvalidContentException(String mesg, int lineNum, int charNum, String[] validChars, char invalidChar) {
        super(mesg);
        mLineNum = lineNum;
        mCharNum = charNum;
        mValidChars = validChars;
        mInvalidChar = invalidChar;
    }

    public int getLineNumber() {
        return mLineNum;
    }

    public int getCharNumber() {
        return mCharNum;
    }

    public String[] getValidChars() {
        return mValidChars;
    }

    public char getInvalidChar() {
        return mInvalidChar;
    }

}
