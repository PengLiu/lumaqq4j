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
package edu.tsinghua.lumaqq.qq.packets.out;

import java.nio.ByteBuffer;

import edu.tsinghua.lumaqq.qq.QQ;
import edu.tsinghua.lumaqq.qq.Util;
import edu.tsinghua.lumaqq.qq.annotation.DocumentalPacket;
import edu.tsinghua.lumaqq.qq.annotation.PacketName;
import edu.tsinghua.lumaqq.qq.annotation.RelatedPacket;
import edu.tsinghua.lumaqq.qq.beans.QQUser;
import edu.tsinghua.lumaqq.qq.packets.BasicOutPacket;
import edu.tsinghua.lumaqq.qq.packets.PacketParseException;
import edu.tsinghua.lumaqq.qq.packets.in.KeepAliveReplyPacket;

/**
 * <pre>
 * Keep Alive包，这个包的格式是
 * 1. 头部
 * 2. 用户QQ号的字符串形式
 * 3. 尾部
 * </pre>
 *
 * @author luma
 */
@DocumentalPacket
@PacketName("Keep Alive请求包")
@RelatedPacket({KeepAliveReplyPacket.class})
public class KeepAlivePacket extends BasicOutPacket {    
    /**
     * 构造函数
     */
    public KeepAlivePacket(QQUser user) {
        super(QQ.QQ_CMD_KEEP_ALIVE, true, user);
        
        // 刻意增加了keep alive包的发送次数，实验性质，不知能否减少网络包
        // 太多情况下的掉线可能性
        sendCount = 10;
    }    
    
    /**
     * @param buf
     * @param length
     * @throws PacketParseException
     */
    public KeepAlivePacket(ByteBuffer buf, int length, QQUser user) throws PacketParseException {
        super(buf, length, user);
    }
    
    /* (non-Javadoc)
     * @see edu.tsinghua.lumaqq.qq.packets.OutPacket#getPacketName()
     */
	@Override
    public String getPacketName() {
        return "Keep Alive Packet";
    }
    
    /* (non-Javadoc)
     * @see edu.tsinghua.lumaqq.qq.packets.OutPacket#putBody(java.nio.ByteBuffer)
     */
	@Override
    protected void putBody(ByteBuffer buf) {
        buf.put(Util.getBytes(String.valueOf(user.getQQ())));
    }
}
