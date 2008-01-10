/*
* LumaQQ - Java QQ Client
*
* Copyright (C) 2004 luma <stubma@163.com>
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
*/
package edu.tsinghua.lumaqq.widgets.rich;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.internal.SWTEventListener;

/**
 * RichBox的事件处理器接口
 * 
 * @author luma
 */
class RichBoxListener extends TypedListener {
    /**
     * 创建一个RichBoxListener
     * 
     * @param listener
     */
    RichBoxListener(SWTEventListener listener) {
        super(listener);
    }

    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    public void handleEvent(Event e) {
        switch (e.type) {
            case RichBox.VerifyKey:
                VerifyEvent verifyEvent = new VerifyEvent(e);
                ((VerifyKeyListener) eventListener).verifyKey(verifyEvent);
                e.doit = verifyEvent.doit;
                break;
        }
    }
}

