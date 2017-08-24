package productgeneration.productArchitecture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import resolutionmodel.api.ResolutionModelService;
import resolutionmodel.implement.ResolutionModel;
import variationpoint.api.VariationPointService;
import variationpoint.implement.VariationPointImpl;
import vspectree.api.VSpecTreeService;
import vspectree.implement.VSpecTreeImpl;
import ACME.ACMEFactory;
import ACME.Attachment;
import ACME.Binding;
import ACME.ComponentInstance;
import ACME.Connector;
import ACME.Link;
import ACME.Property;
import ACME.Representation;
import basemodel.acme.implement.ACMEImpl;
import basemodel.api.BaseArchitectureService;
import cvl.Choice;
import cvl.ChoiceResolution;
import cvl.ObjectExistence;
import cvl.ObjectHandle;
import cvl.ParametricSlotAssignment;
import cvl.VPackage;
import cvl.VSpecResolution;
import cvl.Variable;
import cvl.VariableValueAssignment;
import cvl.VariationPoint;

public class ACMEGeneration {

	private ArrayList<VariationPoint> vpList = new ArrayList<VariationPoint>();
	private ArrayList<VSpecResolution> vspecResolutionList = new ArrayList<VSpecResolution>();

	ArrayList<ComponentInstance> componentList = new ArrayList<ComponentInstance>();
	ArrayList<ComponentInstance> parentComponentList = new ArrayList<ComponentInstance>();
	ArrayList<Connector> connectorList = new ArrayList<Connector>();
	ArrayList<Attachment> attachmentList = new ArrayList<Attachment>();
	ArrayList<Attachment> parentattachmentList = new ArrayList<Attachment>();
	ArrayList<Binding> bindingList = new ArrayList<Binding>();

	ACME.System srcSystem, dstSystem;
	ACMEFactory acmeFactory;

	String resolutionModelFile, variabitlityModelFile, baseModelFile;
	String outDirectory;

	public ACMEGeneration() {
		// TODO Auto-generated constructor stub
	}

	public ACMEGeneration(String resolutionModelFile,
			String variabitlityModelFile, String baseModelFile,
			String outDirectory) {
		this.resolutionModelFile = resolutionModelFile;
		this.variabitlityModelFile = variabitlityModelFile;
		this.baseModelFile = baseModelFile;
		this.outDirectory = outDirectory;

	}

	public void generate() {
		VSpecTreeService vspecTreeService = new VSpecTreeImpl();
		VPackage vPackage = vspecTreeService.getVPackage(variabitlityModelFile);

		VariationPointService variationPointService = new VariationPointImpl();
		this.vpList = variationPointService.getVariationPointList(vPackage);

		BaseArchitectureService baseArchitecture = new ACMEImpl();
		this.srcSystem = baseArchitecture.getACMESystem(baseModelFile);

		this.parentComponentList = baseArchitecture.getParentComponentList(srcSystem);
		this.parentattachmentList = baseArchitecture.getParentAttchmentList(srcSystem);
		this.componentList = baseArchitecture.getComponentList(srcSystem);
		this.connectorList = baseArchitecture.getConnectorList(srcSystem);
		this.attachmentList = baseArchitecture.getAttachmentList(srcSystem);
		this.bindingList = baseArchitecture.getBindingList(srcSystem);

		ResolutionModelService resolutionModel = new ResolutionModel();
		VSpecResolution vSpecResolutionRoot = resolutionModel.getVSpecResolutionRoot(resolutionModelFile);
		this.vspecResolutionList = resolutionModel.getVSPecResolutionList(vSpecResolutionRoot);

		ArrayList<ComponentInstance> activeComponents = this.activeComponents(vspecResolutionList,vpList, componentList);
		ArrayList<Attachment> activeAttachments = this.activeAttachments(activeComponents, attachmentList);
		ArrayList<Binding> activeBindings = this.activeBindings(activeComponents, bindingList);
		
		
		ArrayList<ComponentInstance> availableComponents = this.availableComponents(vspecResolutionList,vpList, componentList);
		
		ArrayList<Property> asignmentProperties = this.getAssignmentProperties(vspecResolutionList,vpList, componentList);
		

		this.acmeFactory = ACMEFactory.eINSTANCE;
		this.dstSystem = this.acmeFactory.createSystem();
		this.dstSystem.setName(srcSystem.getName());

		for (ComponentInstance componentInBaseModel : this.parentComponentList) {
			if (activeComponents.contains(componentInBaseModel)) {
				ComponentInstance dstComponent = componentSearch(
						componentInBaseModel, activeComponents,
						activeAttachments, activeBindings, availableComponents, asignmentProperties);
				this.dstSystem.getComponentDeclaration().add(dstComponent);
			}
		}
		this.dstSystem.getConnectorDeclaration().addAll(this.connectorList);
		for (Attachment attachment : this.parentattachmentList) {
			if (activeAttachments.contains(attachment)) {
				this.dstSystem.getAttachement().add(attachment);
			}
		}

		// write to file
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("acme", new XMIResourceFactoryImpl());
		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();
		// create a resource
		Resource resource = resSet.createResource(URI.createURI(outDirectory
				+ "/product.xmi"));
		// Get the first model element and cast it to the right type, in my
		// example everything is hierarchical included in this first node
		resource.getContents().add(this.dstSystem);
		// now save the content.
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ComponentInstance componentSearch(
			ComponentInstance componentInBaseModel,
			ArrayList<ComponentInstance> activeComponents,
			ArrayList<Attachment> activeAttachments,
			ArrayList<Binding> activeBindings,
			ArrayList<ComponentInstance> availableComponents, ArrayList<Property> asignmentProperties) {
		// TODO Auto-generated method stub

		ComponentInstance dstInstance = this.acmeFactory
				.createComponentInstance();
		dstInstance.setName(componentInBaseModel.getName());

		if (componentInBaseModel.getRepresentations().size() > 0) {
			Representation representation = (Representation) componentInBaseModel.getRepresentations().get(0);
			Representation dstRepresentation = this.acmeFactory.createRepresentation();

			ACME.System srcSubSystem = (ACME.System) representation.getSystems().get(0);

			ACME.System dstSubSystem = this.acmeFactory.createSystem();
			dstSubSystem.setName(srcSubSystem.getName());
			for (ComponentInstance subComponent : srcSubSystem.getComponentDeclaration()) {
				if (activeComponents.contains(subComponent)) {
					dstSubSystem.getComponentDeclaration().add(
							componentSearch(subComponent, activeComponents,
									activeAttachments, activeBindings,
									availableComponents, asignmentProperties));
				}
				if (availableComponents.contains(subComponent)) {
					dstSubSystem.getComponentDeclaration().add(
							componentSearch(subComponent, activeComponents,
									activeAttachments, activeBindings,
									availableComponents, asignmentProperties));
				}
			}
		
			if (srcSubSystem.getConnectorDeclaration() != null) {
				dstSubSystem.getConnectorDeclaration().addAll(
						srcSubSystem.getConnectorDeclaration());
			}
			EList<Link> subAttachmentList = srcSubSystem.getAttachement();
			for (Link link : subAttachmentList) {
				Attachment subAttachment = (Attachment) link;
				if (activeAttachments.contains(subAttachment)) {
					dstSubSystem.getAttachement().add(subAttachment);
				}
			}

			Object subBindingList = srcSubSystem.getBinding();
			for (int i = 0; i < ((EList) subBindingList).size(); i++) {
				Binding subBinding = (Binding) ((EList) subBindingList).get(i);
				
				if (activeBindings.contains(subBinding)) {
					dstSubSystem.getBinding().add(subBinding);
				}
			}

//			EList<Link> subBindingList = subSystem.getBinding();	
//			for (Link link : subBindingList) {
//				Binding subBinding = (Binding) link;
//				if (activeBindings.contains(subBinding)) {
//					dstSystem.getBinding().add(subBinding);
//				}
//			}
			
			dstRepresentation.getSystems().add(dstSubSystem);
			dstInstance.getRepresentations().add(dstRepresentation);
		}
		if (componentInBaseModel.getProperty().size() > 0) {
			for (int i = 0; i < ((EList) componentInBaseModel.getProperty()).size(); i++) {
				Property property = (Property) ((EList) componentInBaseModel
						.getProperty()).get(i);
				if (asignmentProperties.size() > 0) 
				for (Property _property : asignmentProperties) {
					if ( property.equals(_property) ) {
						property.setVal(_property.getVal());

						dstInstance.getProperty().add(property);
					}
				}
			}
		}
		return dstInstance;

	}

	ArrayList<ComponentInstance> activeComponents(ArrayList<VSpecResolution> vspecResolutions, 
			ArrayList<VariationPoint> variationPoints, ArrayList<ComponentInstance> componentsInBaseModel) {
		
		ArrayList<ComponentInstance> componentInstances = new ArrayList<ComponentInstance>();
		for (VSpecResolution vspecResolution : vspecResolutions) {
			if ((vspecResolution instanceof ChoiceResolution)) {
				ChoiceResolution choiceResolution = (ChoiceResolution) vspecResolution;
				if (choiceResolution.isDecision()) {
					Choice choice = choiceResolution.getResolvedChoice();
					for (VariationPoint variationpoint : variationPoints) {
						if ((variationpoint instanceof ObjectExistence)) {
							ObjectExistence objExistence = (ObjectExistence) variationpoint;
							if (objExistence.getBindingChoice().getName()
									.equals(choice.getName())) {
								ObjectHandle objHandle = objExistence
										.getOptionalObject();
								String mofRefComponent = objHandle.getMOFRef();

								ComponentInstance activeComponent = returnComponentByName(
										mofRefComponent, componentsInBaseModel);
								componentInstances.add(activeComponent);
							}
						}
					}
				}
			}
		}
		return componentInstances;
	}

	ArrayList<Property> getAssignmentProperties(ArrayList<VSpecResolution> vspecResolutions, 
			ArrayList<VariationPoint> variationPoints, ArrayList<ComponentInstance> componentsInBaseModel) {
		ArrayList<Property> properties = new ArrayList<Property>();
		for (VSpecResolution vspecResolution : vspecResolutions) {
			if ((vspecResolution instanceof VariableValueAssignment)) {

				VariableValueAssignment variableValueAssignment = (VariableValueAssignment) vspecResolution;
				if (variableValueAssignment.getValue() != null) {
					Variable variable = variableValueAssignment.getResolvedVariable();
					
					for (VariationPoint variationpoint : variationPoints) {
						if ((variationpoint instanceof ParametricSlotAssignment)) {
							ParametricSlotAssignment objExistence = (ParametricSlotAssignment) variationpoint;
							if (objExistence.getBindingVariable().getName()
									.equals(variable.getName())) {
								ObjectHandle objHandle = objExistence.getSlotOwner();
								String mofRefProperty = objHandle.getMOFRef(); //property mof contains a form, e.g. componentName.property

								
								Property property = returnPropertyByName(
										mofRefProperty, componentsInBaseModel);
								if (property != null) property.setVal(variableValueAssignment.getValue());								
								properties.add(property);
							}
						}
					}
				}
			}
		}
		return properties;
	}

	
	ArrayList<Attachment> activeAttachments(ArrayList<ComponentInstance> activeComponents, 
			ArrayList<Attachment> attachments) {
		
		ArrayList<Attachment> activeAttachmentsList = new ArrayList<Attachment>();
		for (Attachment attachment : attachments) {
			String component = attachment.getComp();
			for (ComponentInstance componentInstance : activeComponents) {
				if (componentInstance.getName().equals(component)) {
					activeAttachmentsList.add(attachment);
				}
			}
		}
		return activeAttachmentsList;
	}

	ArrayList<Binding> activeBindings(ArrayList<ComponentInstance> activeComponents,
			ArrayList<Binding> bindings) {
		
		ArrayList<Binding> activeBindingsList = new ArrayList<Binding>();
		for (Binding binding : bindings) {
			String srcComponent = binding.getCompSrc();
			String dstComponent = binding.getCompDest();
			boolean chkSrcComponentInList = false;
			boolean chkDstComponentInList = false;
			for (ComponentInstance componentInstance : activeComponents) {
				if (componentInstance.getName().equals(srcComponent)) {
					chkSrcComponentInList = true;
				}
				if (componentInstance.getName().equals(dstComponent)) {
					chkDstComponentInList = true;
				}
			}
			if ((chkSrcComponentInList) && (chkDstComponentInList)) {
				activeBindingsList.add(binding);
			}
		}
		return activeBindingsList;
	}

	ArrayList<ComponentInstance> availableComponents(ArrayList<VSpecResolution> vspecResolutions, 
			ArrayList<VariationPoint> variationPoints, ArrayList<ComponentInstance> componentsInBaseModel) {

		ArrayList<ComponentInstance> inactiveComponentInstances = new ArrayList<ComponentInstance>();

		for (VSpecResolution vspecResolution : vspecResolutions) {
			if ((vspecResolution instanceof ChoiceResolution)) {
				ChoiceResolution choiceResolution = (ChoiceResolution) vspecResolution;

				if ((!choiceResolution.isDecision())
						&& (choiceResolution.isAvailabilityAtRuntime())) {
					Choice choice = choiceResolution.getResolvedChoice();
					for (VariationPoint variationpoint : variationPoints) {

						if ((variationpoint instanceof ObjectExistence)) {
							ObjectExistence objE = (ObjectExistence) variationpoint;

							if (objE.getBindingChoice().getName()
									.equals(choice.getName())) {
								ObjectHandle objHandle = objE
										.getOptionalObject();
								String mofRefComponent = objHandle.getMOFRef();
								ComponentInstance activeComponent = returnComponentByName(
										mofRefComponent, componentsInBaseModel);
								inactiveComponentInstances.add(activeComponent);
							}
						}
					}
				}
			}
		}
		return inactiveComponentInstances;
	}

	public ComponentInstance returnComponentByName(String name,
			ArrayList<ComponentInstance> sourceComponents) {

		ArrayList<ComponentInstance> listTemp = new ArrayList<ComponentInstance>(
				sourceComponents);
		ComponentInstance component = null;
		for (int i = 0; i < listTemp.size(); i++) {
			if (((ComponentInstance) listTemp.get(i)).getName().equals(name)) {
				return (ComponentInstance) listTemp.get(i);
			}
		}
		return component;
	}

	public Property returnPropertyByName(String name,
			ArrayList<ComponentInstance> sourceComponents) {

		String componentName = name.substring(0, name.lastIndexOf((".")));
		String property = name.substring(name.lastIndexOf(".")+1);
		for (int i = 0; i < sourceComponents.size(); i++) {
			if (((ComponentInstance) sourceComponents.get(i)).getName().equals(componentName)) {
				for (Property pro : sourceComponents.get(i).getProperty()) {
					if (pro.getName().equals(property)) {
						return pro;
					}
				}

			}
		}
		return null;
	}
	
	public static void main(String arg[]) {
		ACMEGeneration f = new ACMEGeneration(
				"/home/DiskD/implementation/process_workspace/"
						+ "TransactionManagement/modelinstances/statetransfersystem/TMSResolutionModelV1.xmi",
				"/home/DiskD/implementation/process_workspace/"
						+ "TransactionManagement/modelinstances/statetransfersystem/TMSVariabilityModel.xmi",
				"/home/DiskD/implementation/process_workspace/"
						+ "TransactionManagement/modelinstances/statetransfersystem/TMSBaseModel.xmi",
				"/home/hnt/Desktop/output/");
		f.generate();
	}

}
