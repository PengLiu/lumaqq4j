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
package edu.tsinghua.lumaqq.widgets.record;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.tsinghua.lumaqq.record.RecordEntry;

/**
 * 列表聊天记录提供者
 * 
 * @author luma
 */
public abstract class ListRecordProvider implements IRecordProvider {
	private RecordEntryListContentProvider contentProvider;
	private static final List<RecordEntry> EMPTY_LIST = new ArrayList<RecordEntry>();
	
	public ListRecordProvider() {
		contentProvider = new RecordEntryListContentProvider(EMPTY_LIST);
	}

	public IWaterfallContentProvider getRecordContentProvider() {
		return contentProvider;
	}

	public Comparator<Object> getRecordSorter() {
		return DefaultRecordSorter.INSTANCE;
	}

	/* (non-Javadoc)
	 * @see edu.tsinghua.lumaqq.widgets.IRecordProvider#setRecords(java.util.List)
	 */
	public void setRecords(List<RecordEntry> records) {
		contentProvider.setSource(records);
	}
	
	/* (non-Javadoc)
	 * @see edu.tsinghua.lumaqq.widgets.IRecordProvider#getRecords()
	 */
	public List<RecordEntry> getRecords() {
		return contentProvider.getSource();
	}
}
