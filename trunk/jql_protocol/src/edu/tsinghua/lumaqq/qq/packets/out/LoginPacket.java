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
import edu.tsinghua.lumaqq.qq.packets.BasicOutPacket;
import edu.tsinghua.lumaqq.qq.packets.PacketParseException;
import edu.tsinghua.lumaqq.qq.packets.in.LoginReplyPacket;


/**
 * <pre>
 * QQ登录请求包，格式为
 * 1. 头部
 * 2. 初始密钥，16字节
 * 3. 用户的密码密钥加密一个空串得到的16字节
 * 4. 36字节的固定内容，未知含义
 * 5. 登录状态，隐身登录还是什么，1字节
 * 6. 16字节固定内容，未知含义
 * 7. 登录令牌长度，1字节
 * 8. 登录令牌
 * 9. 登录模式，1字节，目前只支持普通模式
 * 10. 未知1字节，0x40
 * 11. 后面段的个数，1字节，1个段9字节(猜测)
 * 12. 段，每次基本都是固定内容，未知含义
 * 13. 长度不足则全部填0知道符合登录包长度
 * 14. 尾部
 * </pre>
 *
 * @author luma
 */
@DocumentalPacket
@PacketName("登录请求包")
@RelatedPacket({LoginReplyPacket.class})
public class LoginPacket extends BasicOutPacket {
    /**
     * 构造函数
     */
    public LoginPacket(QQUser user) {
        super(QQ.QQ_CMD_LOGIN, true, user);
    }
	
    /**
     * @param buf
     * @param length
     * @throws PacketParseException
     */
    public LoginPacket(ByteBuffer buf, int length, QQUser user) throws PacketParseException {
        super(buf, length, user);
    }
    
    /* (non-Javadoc)
     * @see edu.tsinghua.lumaqq.qq.packets.OutPacket#getPacketName()
     */
	@Override
    public String getPacketName() {
        return "Login Packet";
    }
    
    /* (non-Javadoc)
     * @see edu.tsinghua.lumaqq.qq.packets.OutPacket#putBody(java.nio.ByteBuffer)
     */
	@Override
    protected void putBody(ByteBuffer buf) {
        // 初始密钥
        buf.put(user.getInitKey());
        // 创建登陆信息数组
        byte[] login = new byte[QQ.QQ_LENGTH_LOGIN_DATA];
        // 开始填充登陆信息
        // 头16个字节用md5处理的密码加密一个空字符串，这用来在服务器端校验密码
        // 其实不一定非要空串，任意均可，只要保证密文是16个字节就行，服务器端
        // 只是看看能不能用密码密钥解密，他不管解密出来的是什么
        System.arraycopy(crypter.encrypt("".getBytes(), user.getPasswordKey()), 0, login, 0, 16);
        // 36字节的固定内容
        System.arraycopy(QQ.QQ_LOGIN_16_51, 0, login, 16, QQ.QQ_LOGIN_16_51.length);
        // 登录状态，隐身登录还是什么，1字节
        login[52] = user.getLoginMode();
        // 16字节固定内容
        System.arraycopy(QQ.QQ_LOGIN_53_68, 0, login, 53, QQ.QQ_LOGIN_53_68.length);
        // 登录令牌长度，1字节
        int pos = 69;
        int len = user.getLoginToken().length;
        login[pos++] = (byte)len;
        // 登录令牌
        System.arraycopy(user.getLoginToken(), 0, login, 70, len);
        pos += len;
        // 未知1字节，0x40
        login[pos++] = 0x40;
        // 后面段的个数，1字节，1个段9字节(猜测)
        // 段，每次基本都是固定内容，未知含义
        System.arraycopy(QQ.QQ_LOGIN_SEGMENTS, 0, login, pos, QQ.QQ_LOGIN_SEGMENTS.length);
        pos += QQ.QQ_LOGIN_SEGMENTS.length;
        // 长度不足则全部填0知道符合登录包长度
        for(; pos < QQ.QQ_LENGTH_LOGIN_DATA; pos++)
            login[pos] = 0;
        // 加密登录信息
        login = crypter.encrypt(login, user.getInitKey());
        // 写入登录信息
        buf.put(login);
    }
}
