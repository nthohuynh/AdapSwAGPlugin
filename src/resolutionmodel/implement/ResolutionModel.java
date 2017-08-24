package resolutionmodel.implement;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import resolutionmodel.api.ResolutionModelService;
import cvl.ChoiceResolution;
import cvl.VPackage;
import cvl.VSpecResolution;
import cvl.cvlPackage;

public class ResolutionModel implements ResolutionModelService{

	public ResolutionModel() {
		
	}
	public VSpecResolution getVSpecResolutionRoot(String resolutionModelFileName) {
		Resource resource = null;
		// TODO Auto-generated constructor stub
		cvlPackage.eINSTANCE.eClass();
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		try {
			//registry extent part of model file ex: *.variability
			reg.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
			reg.getExtensionToFactoryMap().put("cvl", new XMIResourceFactoryImpl());
		} catch (Exception e){
		}
		ResourceSet resourceSet = new ResourceSetImpl();
		String filename = new File(resolutionModelFileName).getAbsolutePath();
		URI uri = URI.createFileURI(filename);
		resource = resourceSet.getResource(uri, true);
		EcoreUtil.resolveAll(resourceSet);
		VPackage vPackage = (VPackage) resource.getContents().get(0);
		 
		ChoiceResolution vspecresolutionRoot = (ChoiceResolution) vPackage.getPackageElement().get(0);
		return vspecresolutionRoot;
	}
	public ArrayList<VSpecResolution> getVSPecResolutionList(VSpecResolution vSpecResolutionRoot) {
		ArrayList<VSpecResolution> vSpecResolutionList = new ArrayList<VSpecResolution>();
		vSpecResolutionList.add(vSpecResolutionRoot);
		for (int i = 0; i < vSpecResolutionRoot.getChild().size(); i++) {
			VSpecResolution vSpecResolutionChild = vSpecResolutionRoot.getChild().get(i); 
			vSpecResolutionList.addAll(getVSPecResolutionList(vSpecResolutionChild));
		}
		return vSpecResolutionList;
	}
	
	

}
