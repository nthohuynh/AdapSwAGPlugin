AdapSwAGPlugin - an Eclipse plugin for generating adaptive product models from models specified in CVL
====================================================================================================

<h1>1. Introduction </h1>

 In order to support generating the adaptive product model, and the executable code in the adaptive software architecture, we have created an Eclipse
plug-in, called Adaptive Software Architecture Generation tool (AdapSwAG
tool). It consists of three modules to validate resolution models, generate the
adaptive product model from the models specified in the domain engineering,
and executable code from the adaptive product model. These modules are integrated into the Eclipse platform as new menu entities

<img src="screenshot/eclipseplugin.png" alt="Mountain View" style="width:304px;height:228px;">

<h1>2. Installation</h1>
In order to install the plugin, it is simple to copy the jar file in the "pluging" directory of the project into the plugin directory of the Eclipse folder. 
</br>
Example: 

```
cp /plugin/AdapSwAGPlugin_1.0.0.201707091449.jar eclipse/plugins/
```
Finally, restart Eclipse
</br>
We tested this plugin with Eclipse Luna 4.4.2.

<h1>3. Utilisation</h1>
In order to use this tool, you should have three models: the variability model, the resolution model, and the base model. The first version of this tool support the base model specified only with ACME metamodel.

<h2> 3.1. Specifying CVL model and base model using EMF Editor </h2>
<h3> Specifying the variability model and the resolution model by using CVL metamodel </h3>
The extended CVL metamodel is placed in the metamodel directory. You can reuse this model to create your variability models from Eclipse EMF Editor.

<img src="screenshot/eclipseplugin3.png" alt="Mountain View" align="middle" style="width:304px;height:228px;">

You search the class VPackage in the metamodel, right click on it, and select Create Dynamic Instance. A new window allows you to set the file name of the variability model and its directory.  

<img src="screenshot/eclipseplugin4.png" lign="center" alt="Mountain View" style="width:304px;height:228px;">

You open the file with Simple Reflective Ecore Model Editor. This editor allows you to create elements in the variability model such as VSpecs, VariationPoints, Constraints.

Similar to the specification of the variability model, the resolution model is created as well, but elements specified in the resolution model are VSpecResolutions. 

<h3>Specifying the base model using ACME metamodel</h3>
In the metamodel directory, we provide an ACME metamodel that is used to create the base model.
The root element in the base model is System that contains Component and Connector (Binding, Attachment).

<h2>3.2. Validating the resolution model </h2> 
The first menu item in the AdapSaWG tools allows openning a window for verifying a resolution model conforming to a variability model. 
</br>
Here, we provide a simple interface that allows to point two file of the variability model and its the corresponding resolution one.
</br>
The results of the validation activity is a message that indicates where conflicts need to be corrected.

<img src="screenshot/eclipseplugin2.png" alt="Mountain View" style="width:304px;height:228px;">

<h2>3.3. Generating adaptive product model </h2> 

<h2>3.4. Generating partially executable code </h2> 


<h1>4. Example</h1>

In the directory Example, we provide two test collections to vefiry how to the plugin performs.


