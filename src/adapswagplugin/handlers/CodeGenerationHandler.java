package adapswagplugin.handlers;

import java.awt.EventQueue;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class CodeGenerationHandler extends AbstractHandler {
	public Object execute(ExecutionEvent event) throws ExecutionException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CodeGeneration window = new CodeGeneration();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return null;
	}
}
