package basemodel.api;

import java.util.ArrayList;

import ACME.Attachment;
import ACME.Binding;
import ACME.ComponentInstance;
import ACME.Connector;

public interface BaseArchitectureService {
//	public Definition getArchitectureDefinition(String file);
//	public ArrayList<Component> getComponentList(Definition definition);
//	public ArrayList<fractal.fractal.Binding> getBindingList(Definition definition);
//	public ArrayList<Component> getParentComponentList(Definition definition);
//	public ArrayList<fractal.fractal.Binding> getParentBindingList(Definition definition);
	
	public ACME.System getACMESystem(String file);
	public ArrayList<ComponentInstance> getParentComponentList(ACME.System sys);
	public ArrayList<Connector> getParentConnectorList(ACME.System sys);
	public ArrayList<Attachment> getParentAttchmentList(ACME.System sys);
	public ArrayList<ACME.Binding> getParentBindingList(ACME.System sys);
	public ArrayList<ComponentInstance> getComponentList(ACME.System srcSystem);
	public ArrayList<Connector> getConnectorList(ACME.System srcSystem);
	public ArrayList<Attachment> getAttachmentList(ACME.System srcSystem);
	public ArrayList<Binding> getBindingList(ACME.System srcSystem);
}
