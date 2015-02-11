package edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread;

import java.util.ArrayList;
import java.util.List;

import org.osate.aadl2.instance.ComponentInstance;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InputPort;

public class ThreadInstance {
  private static int threadIdCounter = 0;
  private int threadId = 0;
  
  private ThreadImplementation impl;
	
	private ComponentInstance aadlInstance;
	private ArrayList<PortConnection> isSrcOfPortConnectionList = new ArrayList<PortConnection>();
	private ArrayList<PortConnection> isDstOfPortConnectionList = new ArrayList<PortConnection>(); 
	private ArrayList<EndpointConnection> isRequiresOfEndpointConnectionList = new ArrayList<>();
	private ArrayList<EndpointConnection> isProvidesOfEndpointConnectionList = new ArrayList<>();
  
	public static void init() {
	  ThreadInstance.threadIdCounter = 0;
	}
	
	//output ISR port/ ISR connection b/w outputisrport and inputeventport
	public ThreadInstance(ThreadImplementation impl) {
	  this.impl = impl;
	  this.threadId = ThreadInstance.threadIdCounter++; 
	}
	
	public String getName() {
	  return (impl.getName() + "_instance_" + getThreadId()).toLowerCase();
	}
	
	public String getGeneratedEntrypoint() {
	  return impl.getGeneratedEntrypoint() + "_Instance_" + getThreadId();
	}
	
	public String getKochabThreadId() {
	  return "TASK_ID_" + (this.impl.getName().toUpperCase());
	}
	
	public List<ThreadInstancePort> getThreadInstanceInputPorts() {
	  ArrayList<ThreadInstancePort> ports = new ArrayList<ThreadInstancePort>();
	  for (InputPort p: this.getThreadImplementation().getInputPorts()) {
	    ThreadInstancePort tip = new ThreadInstancePort(this, p);
	    ports.add(tip);
	  }
	  return ports;
	}
	
	public void addIsSrcOfConnection(PortConnection c) {
		 this.isSrcOfPortConnectionList.add(c);
	}

  public void addIsDstOfConnection(PortConnection c) {
     this.isDstOfPortConnectionList.add(c);
  }

  public ThreadImplementation getThreadImplementation() {
	  return this.impl;
	}
	
  public int getThreadId() {
    return this.threadId;
  }
	public ComponentInstance getAadlInstance() {
	  return this.aadlInstance;
	}
	
	public ArrayList<PortConnection> getIsSrcOfConnectionList() {
		return isSrcOfPortConnectionList;
	}
	
	public ArrayList<PortConnection> getIsDstOfConnectionList() {
	  return isDstOfPortConnectionList;
	}
	
	public int getStackSize() {
		return this.impl.getStackSize();
	}

  /**
   * @return the isSrcOfEndpointConnectionList
   */
  public ArrayList<EndpointConnection> getIsRequiresOfEndpointConnectionList() {
    return isRequiresOfEndpointConnectionList;
  }

  public void addIsRequiresOfEndpointConnectionList(EndpointConnection ec) {
    this.isRequiresOfEndpointConnectionList.add(ec);
  }
  /**
   * @param isSrcOfEndpointConnectionList the isSrcOfEndpointConnectionList to set
   */
  public void setIsRequiresOfEndpointConnectionList(
      ArrayList<EndpointConnection> isRequiresOfEndpointConnectionList) {
    this.isRequiresOfEndpointConnectionList = isRequiresOfEndpointConnectionList;
  }

  /**
   * @return the isDstOfEndpointConnectionList
   */
  public ArrayList<EndpointConnection> getIsProvidesOfEndpointConnectionList() {
    return isProvidesOfEndpointConnectionList;
  }
  
  public void addIsProvidesOfEndpointConnectionList(EndpointConnection ec) {
    this.isProvidesOfEndpointConnectionList.add(ec);
  }

  /**
   * @param isDstOfEndpointConnectionList the isDstOfEndpointConnectionList to set
   */
  public void setIsProvidesOfEndpointConnectionList(
      ArrayList<EndpointConnection> isDstOfEndpointConnectionList) {
    this.isProvidesOfEndpointConnectionList = isDstOfEndpointConnectionList;
  }
	
	
}
