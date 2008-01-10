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
public class SystemTextRecordExporter implements IRecordExporter {
  protected static String nl;
  public static synchronized SystemTextRecordExporter create(String lineSeparator)
  {
    nl = lineSeparator;
    SystemTextRecordExporter result = new SystemTextRecordExporter();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "System Message" + NL + "" + NL + "==============================================================";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL;

	public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	Map<String, Object> params = (Map<String, Object>)argument;
	List<RecordEntry> entries = (List<RecordEntry>)params.get(IRecordExporter.RECORD_ENTRIES);

    stringBuffer.append(TEXT_1);
     for(RecordEntry entry : entries) { 
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    stringBuffer.append( DateTool.format(entry.time) );
    stringBuffer.append(TEXT_4);
    stringBuffer.append( entry.message );
     } 
    return stringBuffer.toString();
  }
}