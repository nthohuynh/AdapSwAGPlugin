package codegeneration;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
//import org.eclipse.emf.mwe.utils.DirectoryCleaner;
import org.eclipse.xpand2.XpandExecutionContextImpl;
import org.eclipse.xpand2.XpandFacade;
import org.eclipse.xpand2.output.Outlet;
import org.eclipse.xpand2.output.Output;
import org.eclipse.xpand2.output.OutputImpl;
import org.eclipse.xtend.typesystem.emf.EmfRegistryMetaModel;

import ACME.*;

public class JavaGeneration {
	public JavaGeneration(String directory, String file) {
		int mc = JOptionPane.WARNING_MESSAGE;;
	    JOptionPane.showMessageDialog(null, "Starting generating code from " + file, "Generation", mc);
	    
	    ACMEPackage.eINSTANCE.eClass();
	    Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
	    ACME.System system = null;
	    try
	    {
	      reg.getExtensionToFactoryMap().put("acme", new XMIResourceFactoryImpl());
	      reg.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
	      URI uri = URI.createFileURI(file);
	      ResourceSet resourceSet = new ResourceSetImpl();
	      Resource resource = resourceSet.getResource(uri, true);
	      
	      system = (ACME.System)resource.getContents().get(0);
	    }
	    catch (Exception localException) {}
	    
	    //xpand
	    Output out = new OutputImpl();
	    out.addOutlet(new Outlet(directory));
	    
	    Object[] params = null;
	    String templatePath = "codegeneration::template::Template::system";
	    
//	    DirectoryCleaner clean = new DirectoryCleaner();
//	    try
//	    {
//	      clean.cleanFolder(directory);
//	    }
//	    catch (FileNotFoundException e)
//	    {
//	      e.printStackTrace();
//	    }
	    XpandExecutionContextImpl execCtx = new XpandExecutionContextImpl(out, null);
	    execCtx.registerMetaModel(new EmfRegistryMetaModel());
	    XpandFacade facade = XpandFacade.create(execCtx);
	    facade.evaluate(templatePath, system, params);
	    
	    JOptionPane.showMessageDialog(null, "Finishing code generation", "Genereted", mc);
	    
	    //System.out.println("End generate code");
	}
}
