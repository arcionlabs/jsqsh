/*
 * Copyright (C) 2007 by Scott C. Gray
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, write to the Free Software Foundation, 675 Mass Ave,
 * Cambridge, MA 02139, USA.
 */
package org.sqsh.commands;

import org.sqsh.Buffer;
import org.sqsh.BufferManager;
import org.sqsh.Command;
import org.sqsh.Session;
import org.sqsh.SessionRedrawBufferMessage;
import org.sqsh.SqshOptions;

/**
 * Implements the \buf-append command.
 */
public class BufCopyOrAppend
    extends Command {
    
    @Override
    public int execute (Session session, SqshOptions options)
        throws Exception {
        
        if (options.arguments.size() < 1
                || options.arguments.size() > 2) {
            
            session.err.println("Use: " + getName() + " src-buf [dst-buf]");
            return 1;
        }
        
        String srcBuf = options.arguments.get(0);
        String destBuf = "!.";
        
        if (options.arguments.size() == 2) {
            
            destBuf = options.arguments.get(1);
        }
        
        BufferManager bufMan = session.getBufferManager();
        Buffer src = bufMan.getBuffer(srcBuf);
        if (src == null) {
            
            session.err.println("The specified source buffer '" 
                + srcBuf + "' does not exist");
            return 1;
        }
        
        Buffer dst = bufMan.getBuffer(destBuf);
        
        if (dst == null) {
            
            session.err.println("The specified destination buffer '" 
                + destBuf + "' does not exist");
            return 1;
        }
        
        /*
         * If this is the "buf-copy" command, then clear the destination
         * buffer before proceeding.
         */
        if (getName().contains("copy")) {
            
            dst.clear();
        }
        
        boolean doRedraw = (dst == bufMan.getCurrent());
        dst.add(src.toString());
        
        /*
         * If we just manipulated the current buffer, then ask the session
         * to redraw the current buffer.
         */
        if (doRedraw) {
            
            throw new SessionRedrawBufferMessage();
        }
        
        return 0;
    }
}
