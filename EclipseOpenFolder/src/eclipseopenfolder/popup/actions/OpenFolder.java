package eclipseopenfolder.popup.actions;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * 
 * 打开文件所在目录
 * @author 洪 qq:2260806429
 * 
 */
public class OpenFolder implements IObjectActionDelegate {

	/**
	 * Constructor for Action1.
	 */
	public OpenFolder() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	private IResource res;

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		if (null == res) {
			return;
		}
		File file = res.getLocation().toFile();
		if (file.exists()) {
			if (file.isFile()) {
				file = file.getParentFile();
			}
			try {
				Desktop.getDesktop().open(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if (null != selection && selection instanceof StructuredSelection) {
			StructuredSelection sel = (StructuredSelection) selection;
			res = (IResource) sel.getFirstElement();
		}
	}

}
