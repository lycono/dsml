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
package org.lycono.dsml;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.*;

import org.lycono.dsml.parser.*;
import org.lycono.dsml.model.*;
import org.lycono.dsml.writer.*;
import org.lycono.dsml.impl.parser.*;
import org.lycono.dsml.impl.writer.*;

/**
 * Command line entry point for simple dsml operations. Invoke with no
 * parameters for usage.
 *
 * @author Matt Bateman
 */
public class Main {

    private static final String USAGE =
        "USAGE: \n" +
        "  dsml.Main <file name> [-print] [-statedebug] [-parsedebug] [-ctx <impl>]";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(USAGE);
            return;
        }
        String fileName = args[0];
        boolean stateDebug = false;
        boolean parseDebug = false;
        boolean print = false;
        String ctxImpl = null;

        for (int i = 1; i < args.length; i++) {
            if (args[i].equals("-statedebug")) {
                stateDebug = true;
            }
            else if (args[i].equals("-parsedebug")) {
                parseDebug = true;
            }
            else if (args[i].equals("-print")) {
                print = true;
            }
            else if (args[i].equals("-ctx")) {
                ctxImpl = args[++i];
            }
        }

        Reader in = null;
        try {
            in = new BufferedReader(new FileReader(fileName));
            ParseContext ctx = null;
            if (ctxImpl != null) {
                try {
                    Class<?> clazz = Class.forName(ctxImpl);
                    ctx = (ParseContext) clazz.newInstance();
                }
                catch (Exception e) {
                    System.err.println("Could not load ParseContext implementation: " + ctxImpl);
                    e.printStackTrace();
                    return;
                }
            }
            else {
                ModelBuildingContext mbc = DefaultFactory.createModelBuildingContext();
                mbc.setDebug(parseDebug);
                ctx = mbc;
            }

            DefaultParser parser = DefaultFactory.createParser();
            parser.setStateDebug(stateDebug);
            parser.parse(ctx, in);

            if (print) {
                Method getMethod = ctx.getClass().getMethod("getDocument", new Class[0]);
                if (getMethod != null) {
                    Document doc = (Document) getMethod.invoke(ctx);
                    Writer writer = new DefaultWriter();
                    writer.write(doc, System.out);
                }
                else {
                    System.err.println("ParseContext does not build a Document.");
                }
            }
        }
        catch (InvalidContentException e) {
            System.err.println(e.getMessage());
        }
        catch (Exception e) {
            System.err.println("An error occurred parsing the file.");
            e.printStackTrace();
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (Exception e) { e.printStackTrace(); }
            }
        }
    }

}
