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
package edu.tsinghua.lumaqq.template.record;


import edu.tsinghua.lumaqq.widgets.record.*;
import edu.tsinghua.lumaqq.models.*;
import edu.tsinghua.lumaqq.record.*;
import edu.tsinghua.lumaqq.ui.helper.DateTool;
import java.util.Map;
import java.util.List;

/**
 * 导出器实现类 
 *
 * @author luma
 */
@SuppressWarnings("unchecked")
public class SMSHtmlRecordExporter implements IRecordExporter {
  protected static String nl;
  public static synchronized SMSHtmlRecordExporter create(String lineSeparator)
  {
    nl = lineSeparator;
    SMSHtmlRecordExporter result = new SMSHtmlRecordExporter();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<HTML>" + NL + "<HEAD>" + NL + "<TITLE>(";
  protected final String TEXT_2 = ")";
  protected final String TEXT_3 = " And (";
  protected final String TEXT_4 = ")";
  protected final String TEXT_5 = "</TITLE>" + NL + "</HEAD>" + NL + "" + NL + "<BODY>" + NL + "<center><b>(";
  protected final String TEXT_6 = ")";
  protected final String TEXT_7 = " And (";
  protected final String TEXT_8 = ")";
  protected final String TEXT_9 = "</b></center><br/>" + NL + "<TABLE width=\"100%\" border=1 cellpadding=3>" + NL + "<TR bgcolor=\"#718BD6\">" + NL + "\t<TH><font color=\"#FFFFFF\">Nick</font></TH>" + NL + "\t<TH><font color=\"#FFFFFF\">Time</font></TH>" + NL + "\t<TH><font color=\"#FFFFFF\">Message</font></TH>" + NL + "</TR>";
  protected final String TEXT_10 = NL + "\t<TR ";
  protected final String TEXT_11 = "bgcolor=\"#F6F6F6\"";
  protected final String TEXT_12 = "bgcolor=\"#FFFFFF\"";
  protected final String TEXT_13 = ">" + NL + "\t\t<TD><font color=\"#006699\">";
  protected final String TEXT_14 = "</font></TD>" + NL + "\t\t<TD><font color=\"#000000\">";
  protected final String TEXT_15 = "</font></TD>" + NL + "\t\t<TD><font color=\"#006699\">";
  protected final String TEXT_16 = "</font></TD>" + NL + "\t</TR>";
  protected final String TEXT_17 = NL + "</TABLE>" + NL + "</BODY>" + NL + "</HTML>";

	public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	Map<String, Object> params = (Map<String, Object>)argument;
	User me = (User)params.get(IRecordExporter.MY_MODEL);
	String mobile = (String)params.get(IRecordExporter.MOBILE_NUMBER);
	List<RecordEntry> entries = (List<RecordEntry>)params.get(IRecordExporter.RECORD_ENTRIES);

    stringBuffer.append(TEXT_1);
    stringBuffer.append( me.qq );
    stringBuffer.append(TEXT_2);
    stringBuffer.append( me.displayName );
    stringBuffer.append(TEXT_3);
    stringBuffer.append( mobile );
    stringBuffer.append(TEXT_4);
    stringBuffer.append( mobile );
    stringBuffer.append(TEXT_5);
    stringBuffer.append( me.qq );
    stringBuffer.append(TEXT_6);
    stringBuffer.append( me.displayName );
    stringBuffer.append(TEXT_7);
    stringBuffer.append( mobile );
    stringBuffer.append(TEXT_8);
    stringBuffer.append( mobile );
    stringBuffer.append(TEXT_9);
     for(RecordEntry entry : entries) { 
    stringBuffer.append(TEXT_10);
     if(entry.sender == 0) { 
    stringBuffer.append(TEXT_11);
     } else { 
    stringBuffer.append(TEXT_12);
     } 
    stringBuffer.append(TEXT_13);
     if(entry.sender == 0) { 
    stringBuffer.append( mobile );
     } else { 
    stringBuffer.append( me.displayName );
     } 
    stringBuffer.append(TEXT_14);
    stringBuffer.append( DateTool.format(entry.time) );
    stringBuffer.append(TEXT_15);
    stringBuffer.append( entry.message );
    stringBuffer.append(TEXT_16);
     } 
    stringBuffer.append(TEXT_17);
    return stringBuffer.toString();
  }
}