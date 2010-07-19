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
package org.lycono.dsml.impl.writer;

import java.io.IOException;
import java.util.*;

import org.lycono.dsml.model.*;
import org.lycono.dsml.writer.*;

/**
 * Default implementation of Writer.
 *
 * @author Matt Bateman
 */
public class DefaultWriter implements Writer {

    private boolean mPrettyPrint;

    public DefaultWriter() {
        mPrettyPrint = true;
    }

    private void write(Node node, java.io.Writer out, String indent) throws IOException {
        if (mPrettyPrint) {
            out.write(indent);
        }
        out.write("(");
        out.write(node.getName());
        Iterator<Attribute> attrs = node.listAttributes();
        while (attrs.hasNext()) {
            out.write(" ");
            Attribute attr = attrs.next();
            out.write(attr.getName());
            out.write("=\"");
            out.write(attr.getValue());
            out.write("\"");
        }
        if ((node.getValue() != null) && (!node.getValue().equals(""))) {
            out.write(" ");
            out.write("\"");
            out.write(node.getValue());
            out.write("\"");
        }
        else if (node.hasChildren()) {
            out.write("\n");
            Iterator<Node> kids = node.listChildren();
            while (kids.hasNext()) {
                write(kids.next(), out, indent + " ");
            }
        }
        out.write(")");
        out.write("\n");
    }

    public void setPrettyPrint(boolean b) {
        mPrettyPrint = b;
    }

    public void write(Document doc, java.io.Writer out) throws IOException {
        Iterator<Node> roots = doc.listRoots();
        while (roots.hasNext()) {
            write(roots.next(), out);
        }
        out.flush();
    }

    public void write(Node node, java.io.Writer out) throws IOException {
        write(node, out, "");
        out.flush();
    }

    public void write(Document doc, java.io.OutputStream out) throws IOException {
        java.io.OutputStreamWriter osw = new java.io.OutputStreamWriter(out);
        Iterator<Node> roots = doc.listRoots();
        while (roots.hasNext()) {
            write(roots.next(), osw);
        }
        osw.flush();
    }

    public void write(Node node, java.io.OutputStream out) throws IOException {
        java.io.OutputStreamWriter osw = new java.io.OutputStreamWriter(out);
        write(node, osw);
        osw.flush();
    }

}
