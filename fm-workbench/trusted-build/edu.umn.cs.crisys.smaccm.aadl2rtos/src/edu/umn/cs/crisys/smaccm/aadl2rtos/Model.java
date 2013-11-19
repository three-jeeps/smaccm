/*
Copyright (c) 2011, 2013 Rockwell Collins and the University of Minnesota.
Developed with the sponsorship of the Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data,
including any software or models in source or binary form, as well as any drawings, specifications,
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
 */

package edu.umn.cs.crisys.smaccm.aadl2rtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.aadl2.instance.SystemInstance;

import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.Connection;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.InterruptServiceRoutine;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.MyPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.SharedData;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.ThreadCalendar;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.ThreadInstance;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.ThreadInstancePort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.ThreadUtil;

public class Model {
	private SystemImplementation systemImplementation;
	private SystemInstance systemInstance;

	// Connection instances - drives number of semaphores
	// (one function per thread implementation, pass in thread instance id)
	private List<Connection> connectionInstances = new ArrayList<Connection>();
	// private ArrayList<String> semaphoreList = new ArrayList<String>();

	// Implementation objects
	public Map<ThreadTypeImpl, ThreadImplementation> threadImplementationMap;
	public List<ThreadImplementation> threadImplementationList;
	public List<InterruptServiceRoutine> isrList;
	public List<SharedData> sharedDataList;
	public List<String> legacySemaphoreList;
	public ThreadCalendar threadCalendar;
	public Set<String> sourceFiles = new HashSet<String>(); 
	
	// private Map<ThreadImplementation, Set<Pair<MyPort, MyPort>>> threadSourcePorts = new HashMap<ThreadImplementation, Set<Pair<MyPort, MyPort>>>();

	// Model constructor
	public Model(SystemImplementation sysImpl, 
	             SystemInstance sysInst, 
	             Map<ThreadTypeImpl, ThreadImplementation> threadImplementationMap, 
               List<Connection> connectionInstances, 
               List<InterruptServiceRoutine> isrList, 
               ThreadCalendar threadCalendar, 
               Set<String> sourceFiles, 
               List<SharedData> sharedDataList,
               List<String> legacySemaphoreList) {
	  this.sharedDataList = sharedDataList; 
	  this.sourceFiles = sourceFiles;
		this.threadImplementationMap = threadImplementationMap;
		this.threadImplementationList = 
		      new ArrayList<ThreadImplementation>(threadImplementationMap.values());
		this.isrList = isrList;
		this.systemImplementation = sysImpl;
		this.systemInstance = sysInst;
		this.connectionInstances = connectionInstances;
		this.threadCalendar = threadCalendar;
		this.legacySemaphoreList = legacySemaphoreList;
		// this.threadSourcePorts = threadSourcePorts;

		// createSemaphoreList();
	}

	public List<ThreadInstance> getDestinationThreadsForPort(MyPort pi) {
		List<ThreadInstance> destThreads = new ArrayList<ThreadInstance>();

		// find all destinations of the given connection
		for (Connection ci : pi.getConnections()) {
		  destThreads.add(ci.getDestThreadInstance());
		}
		return destThreads;
	}

	public SystemImplementation getSystemImplementation() {
		return systemImplementation;
	}

	public SystemInstance getSystemInstance() {
		return systemInstance;
	}

	public Set<String> getSourceFiles()  {
	  return this.sourceFiles; 
	}
	
	public void addSourceFile(String fileName) {
	  this.sourceFiles.add(fileName);
	}
	
	public List<InterruptServiceRoutine> getISRList() {
	  return this.isrList;
	}
	
	public String getSystemInstanceName() {
		return systemInstance.getName();
	}
	
	public ThreadCalendar getThreadCalendar() {
	  return this.threadCalendar;
	}
  
	public List<SharedData> getSharedDataList() {
	  return this.sharedDataList;
	}
/*	public List<ThreadImplementation> getTaskThreads() {
		return ThreadUtil.getTaskThreads(threadImplementationMap.values());
	}

	public List<ThreadImplementation> getISRThreads() {
		return ThreadUtil.getISRThreads(threadImplementationMap.values());
	}
*/
	
	public List<ThreadImplementation> getAllThreads() {
		return this.threadImplementationList;
	}
	
	public List<ThreadInstance> getAllThreadInstances() {
	  List<ThreadInstance> list = new ArrayList<ThreadInstance>(); 
	  for (ThreadImplementation t: getAllThreads()) {
	    list.addAll(t.getThreadInstanceList()); 
	  }
	  return list;
	}
	
	public List<ThreadInstancePort> getAllThreadInstanceInputPorts() {
	  ArrayList<ThreadInstancePort> instances = 
	      new ArrayList<ThreadInstancePort>();
	  for (ThreadInstance ti: getAllThreadInstances()) {
	    instances.addAll(ti.getThreadInstanceInputPorts());
	  }
	  return instances;
	}
	
/*
	public Map<ThreadImplementation, Set<Pair<MyPort, MyPort>>> getThreadSourcePorts() {
		return threadSourcePorts;
	}
*/	

	public List<ThreadImplementation> getThreads(List<String> threadNameList) {
		List<ThreadImplementation> threadList = new ArrayList<ThreadImplementation>();
		for (String threadName : threadNameList) { 
			threadList.add(threadImplementationMap.get(threadName));
		}
		return threadList;
	}

	public List<Connection> getConnections() {
	  return this.connectionInstances;
	}

	public List<String> getLegacySemaphoreList() {
		return this.legacySemaphoreList;
	}
}