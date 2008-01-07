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
import edu.tsinghua.lumaqq.qq.annotation.DocumentalPacket;
import edu.tsinghua.lumaqq.qq.annotation.PacketName;
import edu.tsinghua.lumaqq.qq.annotation.RelatedPacket;
import edu.tsinghua.lumaqq.qq.beans.QQUser;
import edu.tsinghua.lumaqq.qq.packets.PacketParseException;
import edu.tsinghua.lumaqq.qq.packets.in.ClusterCommandReplyPacket;

/**
 * <pre>
 * 搜索群的包，格式为：
 * 1. 头部
 * 2. 命令类型，1字节，搜索群是0x06
 * 3. 查找方式，1字节，是搜索示范群还是根据ID搜索等等
 * 4. 群的外部ID，4字节，如果是搜索示范群，全0
 * 5. 尾部
 * </pre>
 * 
 * @author luma
 */
@DocumentalPacket
@PacketName("搜索群请求包")
@RelatedPacket({ClusterCommandReplyPacket.class})
public class ClusterSearchPacket extends ClusterCommandPacket {
	private byte searchType;
	private int externalId;
	
	/**
	 * 构造函数
	 */
	public ClusterSearchPacket(QQUser user) {
		super(user);		
		subCommand = QQ.QQ_CLUSTER_CMD_SEARCH_CLUSTER;
	}

    /**
     * @param buf
     * @param length
     * @throws PacketParseException
     */
    public ClusterSearchPacket(ByteBuffer buf, int length, QQUser user)
            throws PacketParseException {
        super(buf, length, user);
    }
    
    /* (non-Javadoc)
     * @see edu.tsinghua.lumaqq.qq.packets.OutPacket#getPacketName()
     */
	@Override
    public String getPacketName() {
        return "Cluster Search Packet";
    }
    
    /* (non-Javadoc)
     * @see edu.tsinghua.lumaqq.qq.packets.OutPacket#putBody(java.nio.ByteBuffer)
     */
	@Override
    protected void putBody(ByteBuffer buf) {
		// 命令类型
		buf.put(subCommand);
		// 群类型
		buf.put(searchType);
		// 内部ID
		if(searchType == QQ.QQ_CLUSTER_SEARCH_DEMO)
			buf.putInt(0);
		else
		    buf.putInt(externalId);
    }
    
    /**
	 * @return Returns the externalId.
	 */
	public int getExternalId() {
		return externalId;
	}
	
	/**
	 * @param externalId The externalId to set.
	 */
	public void setExternalId(int externalId) {
		this.externalId = externalId;
	}
	
	/**
	 * @return Returns the searchType.
	 */
	public byte getSearchType() {
		return searchType;
	}
	
	/**
	 * @param searchType The searchType to set.
	 */
	public void setSearchType(byte searchType) {
		this.searchType = searchType;
	}
}