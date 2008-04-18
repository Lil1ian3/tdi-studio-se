// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.core.ui;

import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;
import org.epic.perleditor.PerlEditorPlugin;
import org.talend.commons.exception.BusinessException;
import org.talend.commons.exception.MessageBoxExceptionHandler;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.context.UpdateRunJobComponentContextHelper;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.context.JobContextManager;
import org.talend.core.model.process.IContextManager;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.IProcess2;
import org.talend.core.ui.IUIRefresher;
import org.talend.core.utils.AccessingEmfJob;
import org.talend.designer.core.DesignerPlugin;
import org.talend.designer.core.ISyntaxCheckableEditor;
import org.talend.designer.core.ui.editor.AbstractTalendEditor;
import org.talend.designer.core.ui.editor.CodeEditorFactory;
import org.talend.designer.core.ui.editor.TalendJavaEditor;
import org.talend.designer.core.ui.editor.TalendPerlEditor;
import org.talend.designer.core.ui.editor.nodecontainer.NodeContainerPart;
import org.talend.designer.core.ui.editor.nodes.Node;
import org.talend.designer.core.ui.editor.nodes.NodeLabel;
import org.talend.designer.core.ui.editor.nodes.NodeLabelEditPart;
import org.talend.designer.core.ui.editor.nodes.NodePart;
import org.talend.designer.core.ui.editor.outline.NodeTreeEditPart;
import org.talend.designer.core.ui.editor.process.Process;
import org.talend.designer.core.ui.editor.process.ProcessPart;
import org.talend.designer.core.ui.editor.subjobcontainer.SubjobContainerPart;
import org.talend.designer.runprocess.IProcessor;
import org.talend.designer.runprocess.ProcessorException;
import org.talend.designer.runprocess.ProcessorUtilities;
import org.talend.repository.editor.RepositoryEditorInput;
import org.talend.repository.job.deletion.JobResourceManager;
import org.talend.repository.model.IProxyRepositoryFactory;
import org.talend.repository.model.IRepositoryService;
import org.talend.repository.ui.views.IRepositoryView;

/**
 * DOC qzhang class global comment. Detailled comment
 */
public abstract class AbstractMultiPageTalendEditor extends MultiPageEditorPart implements IResourceChangeListener,
        ISelectionListener, IUIRefresher {

    protected AdapterImpl dirtyListener = new AdapterImpl() {

        @Override
        public void notifyChanged(Notification notification) {
            if (notification.getEventType() != Notification.REMOVING_ADAPTER) {
                propertyIsDirty = true;
                designerEditor.getProperty().eAdapters().remove(dirtyListener);
                process.updateProperties();
                designerEditor.getProperty().eAdapters().add(dirtyListener);
                firePropertyChange(IEditorPart.PROP_DIRTY);
            }
        }
    };

    public abstract boolean showExtraPaletteEntry();

    protected boolean propertyIsDirty = false;

    protected AbstractDecoratedTextEditor codeEditor;

    protected IProcess2 process;

    protected IProcessor processor;

    protected String oldJobName;

    protected boolean keepPropertyLocked; // used only if the user try to open more than one editor at a time.

    private RepositoryEditorInput processEditorInput;

    protected AbstractTalendEditor designerEditor;

    public AbstractMultiPageTalendEditor() {
        super();

        ActiveProcessTracker.initialize();

        // Display.getDefault().asyncExec(new Runnable() {
        //
        // public void run() {
        // try {
        // CorePlugin.getDefault().getCodeGeneratorService().createRoutineSynchronizer().syncAllRoutines();
        // } catch (Exception e) {
        // ExceptionHandler.process(e);
        // }
        // }
        // });

        ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
    }

    @Override
    public boolean isDirty() {
        return propertyIsDirty || super.isDirty();
    }

    public void setReadOnly(boolean readonly) {
        designerEditor.setReadOnly(readonly);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.part.MultiPageEditorPart#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
     */
    @Override
    public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
        setSite(site);
        setInput(editorInput);
        site.setSelectionProvider(new MultiPageTalendSelectionProvider(this));
        getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(this);

        // Lock the process :
        IRepositoryService service = CorePlugin.getDefault().getRepositoryService();
        IProxyRepositoryFactory repFactory = service.getProxyRepositoryFactory();
        processEditorInput = (RepositoryEditorInput) editorInput;
        IProcess2 currentProcess = processEditorInput.getLoadedProcess();
        if (!currentProcess.isReadOnly()) {
            try {
                processEditorInput.getItem().getProperty().eAdapters().add(dirtyListener);
                repFactory.lock(currentProcess);
            } catch (PersistenceException e) {
                e.printStackTrace();
            } catch (BusinessException e) {
                // Nothing to do
            }
        } else {
            setReadOnly(true);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.editor.INameRefresher#refreshName()
     */
    public void refreshName() {
        try {
            JobResourceManager jobResourceManager = JobResourceManager.getInstance();
            jobResourceManager.removeProtection(designerEditor);
            for (String id : designerEditor.getProtectedIds()) {
                if (designerEditor.getJobResource(id).getJobInfo().getJobName().equalsIgnoreCase(oldJobName)) {
                    // delete only the job renamed
                    jobResourceManager.deleteResource(designerEditor.getJobResource(id));
                }
            }
            designerEditor.resetJobResources();

            setName();
            designerEditor.getCurrentJobResource().getJobInfo().setJobName(getEditorInput().getName());
            jobResourceManager.addProtection(designerEditor);

            processor.initPath();
            processor.setProcessorStates(IProcessor.STATES_EDIT);

            FileEditorInput input = createFileEditorInput();
            codeEditor.setInput(input);

            IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            if (activeWorkbenchWindow != null) {
                if (activeWorkbenchWindow.getActivePage().isPartVisible(this)) {
                    new ActiveProcessTracker().partBroughtToTop(this);
                    DesignerPlugin.getDefault().getRunProcessService().refreshView();
                }
            }

        } catch (Exception e) {
            MessageBoxExceptionHandler.process(e);
        }
    }

    /**
     * DOC bqian Comment method "selectNode".
     * 
     * @param node
     */
    @SuppressWarnings("unchecked")
    public void selectNode(Node node) {
        GraphicalViewer viewer = designerEditor.getViewer();
        Object object = viewer.getRootEditPart().getChildren().get(0);
        if (object instanceof ProcessPart) {
            // the structure in memory is:
            // ProcessPart < SubjobContainerPart < NodeContainerPart < NodePart
            for (EditPart editPart : (List<EditPart>) ((ProcessPart) object).getChildren()) {
                if (editPart instanceof SubjobContainerPart) {
                    SubjobContainerPart subjobPart = (SubjobContainerPart) editPart;
                    for (EditPart part : (List<EditPart>) subjobPart.getChildren()) {
                        if (part instanceof NodeContainerPart) {
                            EditPart nodePart = (EditPart) part.getChildren().get(0);
                            if (nodePart instanceof NodePart) {
                                if (((NodePart) nodePart).getModel().equals(node)) {
                                    viewer.select(nodePart);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Calculates the contents of page 2 when the it is activated.
     */
    @Override
    protected void pageChange(final int newPageIndex) {
        super.pageChange(newPageIndex);
        setName();
        if (newPageIndex == 1) {
            if (codeEditor instanceof ISyntaxCheckableEditor) {
                moveCursorToSelectedComponent();

                /*
                 * Belowing method had been called at line 331 within the generateCode method, as soon as code
                 * generated.
                 */
                // ((ISyntaxCheckableEditor) codeEditor).validateSyntax();
            }
            codeSync();
        }
    }

    /**
     * Move Cursor to Selected Node.
     * 
     * @param processor
     */
    private void moveCursorToSelectedComponent() {
        String nodeName = getSelectedNodeName();
        if (nodeName != null) {
            if (codeEditor instanceof TalendJavaEditor) {
                ((TalendJavaEditor) codeEditor).placeCursorTo(nodeName); //$NON-NLS-1$ //$NON-NLS-2$
            } else {
                ((TalendPerlEditor) codeEditor).placeCursorTo(nodeName); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
    }

    public void setName() {
        String label = getEditorInput().getName();
        oldJobName = label;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.part.MultiPageEditorPart#createPages()
     */
    @Override
    protected void createPages() {
        createPage0();
        createPage1();
    }

    protected void createPage0() {
        try {
            int index = addPage(designerEditor, getEditorInput());
            setPageText(index, "Designer");
            designerEditor.setParent(this);
        } catch (PartInitException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates page 1 of the multi-page editor, which allows you to change the font used in page 2.
     */
    protected void createPage1() {
        codeEditor = CodeEditorFactory.getInstance().getCodeEditor(getCurrentLang());
        process = designerEditor.getProcess();
        ((Process) process).setEditor(this);
        processor = ProcessorUtilities.getProcessor(process, process.getContextManager().getDefaultContext());

        process.setProcessor(processor);
        if (processor.getProcessorType().equals("javaProcessor")) { //$NON-NLS-1$
            processor.setProcessorStates(IProcessor.STATES_EDIT);
            if (codeEditor instanceof ISyntaxCheckableEditor) {
                processor.setSyntaxCheckableEditor((ISyntaxCheckableEditor) codeEditor);
            }
        }

        try {
            int index = addPage(codeEditor, createFileEditorInput());
            // init Syntax Validation.
            if (getCurrentLang() == ECodeLanguage.PERL) {
                PerlEditorPlugin.getDefault().setSyntaxValidationPreference(true);
            }
            setPageText(index, "Code");

        } catch (PartInitException pie) {
            pie.printStackTrace();
        }

        generateCode();
    }

    /**
     * DOC bqian Comment method "generateCode".
     */
    private void generateCode() {
        if (process.getGeneratingNodes().size() != 0) {
            Job job = new AccessingEmfJob("Generating code") {

                @Override
                protected IStatus doRun(IProgressMonitor monitor) {
                    ProcessorUtilities.generateCode(process, process.getContextManager().getDefaultContext(), false, false, true,
                            ProcessorUtilities.GENERATE_WITH_FIRST_CHILD);

                    return Status.OK_STATUS;
                }
            };
            job.setUser(true);
            job.setPriority(Job.BUILD);
            job.schedule(); // start as soon as possible
        }
    }

    /**
     * Creates page 1 of the multi-page editor, which allows you to change the font used in page 2.
     */

    /**
     * get the current project generating code language.
     * 
     * @return the current generating code language
     */
    private ECodeLanguage getCurrentLang() {
        return ((RepositoryContext) CorePlugin.getContext().getProperty(Context.REPOSITORY_CONTEXT_KEY)).getProject()
                .getLanguage();
    }

    /**
     * Saves the multi-page editor's document.
     */
    @Override
    public void doSave(final IProgressMonitor monitor) {
        if (!isDirty()) {
            return;
        }
        updateRunJobContext();
        designerEditor.getProperty().eAdapters().remove(dirtyListener);
        getEditor(0).doSave(monitor);
        designerEditor.getProperty().eAdapters().add(dirtyListener);
        codeSync();

        propertyIsDirty = false;
        firePropertyChange(IEditorPart.PROP_DIRTY);
    }

    protected void updateRunJobContext() {
        JobContextManager manager = (JobContextManager) getProcess().getContextManager();
        Map<String, String> nameMap = manager.getNameMap();
        if (manager.isModified()) {
            try {
                IProxyRepositoryFactory factory = CorePlugin.getDefault().getProxyRepositoryFactory();

                Set<String> curContextVars = getCurrentContextVariables(manager);
                String jobId = getProcess().getProperty().getId();

                UpdateRunJobComponentContextHelper.updateItemRunJobComponentReference(factory, nameMap, jobId, curContextVars);

                IEditorReference[] reference = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .getEditorReferences();
                List<IProcess> processes = CorePlugin.getDefault().getDesignerCoreService().getOpenedProcess(reference);

                UpdateRunJobComponentContextHelper.updateOpenedJobRunJobComponentReference(processes, nameMap, jobId,
                        curContextVars);
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
            nameMap.clear();
            manager.setModified(false);
        }
    }

    private Set<String> getCurrentContextVariables(IContextManager manager) {
        Set<String> varNameSet = new HashSet<String>();
        if (manager != null) {
            for (IContextParameter param : manager.getDefaultContext().getContextParameterList()) {
                varNameSet.add(param.getName());
            }
        }
        return varNameSet;
    }

    public void codeSync() {
        // force the code sync each time, need to add another feature to enhance this later.

        // if (process.isProcessModified() && process.getGeneratingNodes().size() != 0) {
        try {
            processor.generateCode(false, false, true);

        } catch (ProcessorException pe) {
            MessageBoxExceptionHandler.process(pe);
        }
        // }
    }

    /**
     * Saves the multi-page editor's document as another file. Also updates the text for page 0's tab, and updates this
     * multi-page editor's input to correspond to the nested editor's.
     */
    @Override
    public void doSaveAs() {
        IEditorPart editor = getEditor(0);
        editor.doSaveAs();
        setPageText(0, editor.getTitle());
        setInput(editor.getEditorInput());
    }

    /*
     * (non-Javadoc) Method declared on IEditorPart
     */
    public void gotoMarker(final IMarker marker) {
        setActivePage(0);
    }

    /*
     * (non-Javadoc) Method declared on IEditorPart.
     */
    @Override
    public boolean isSaveAsAllowed() {
        return true;
    }

    public void showDesignerPage() {
        setActivePage(0);
    }

    public void showCodePage() {
        setActivePage(1);
    }

    /**
     * Move Cursor to Selected Node.
     * 
     * @param processor
     */

    /**
     * Get the selected Node if any.
     * 
     * @return the component selected name or null if component is not found or is not activated
     */
    public String getSelectedNodeName() {
        String nodeName = null;
        Node node = getSelectedGraphicNode();
        if (node != null) {
            if (node.isActivate() || node.isDummy()) {
                nodeName = node.getUniqueName();
            } else {
                nodeName = null;

            }
            if (node.getComponent().getMultipleComponentManagers().size() > 0) {
                nodeName += "_" + node.getComponent().getMultipleComponentManagers().get(0).getInput().getName(); //$NON-NLS-1$
            }
        }
        return nodeName;
    }

    /**
     * DOC amaumont Comment method "getSelectedNode".
     * 
     * @return
     */
    public Node getSelectedGraphicNode() {
        Node node = null;
        List selections = designerEditor.getViewer().getSelectedEditParts();
        if (selections.size() == 1) {
            Object selection = selections.get(0);

            if (selection instanceof NodeTreeEditPart) {
                NodeTreeEditPart nTreePart = (NodeTreeEditPart) selection;
                node = (Node) nTreePart.getModel();
            } else {
                if (selection instanceof NodePart) {
                    NodePart editPart = (NodePart) selection;
                    node = (Node) editPart.getModel();
                } else if (selection instanceof NodeLabelEditPart) {
                    NodeLabelEditPart editPart = (NodeLabelEditPart) selection;
                    node = ((NodeLabel) editPart.getModel()).getNode();
                }
            }
        }
        return node;
    }

    /**
     * Closes all project files on project close.
     */

    public void resourceChanged(final IResourceChangeEvent event) {
        if (event.getType() == IResourceChangeEvent.PRE_CLOSE) {
            Display.getDefault().asyncExec(new Runnable() {

                public void run() {
                    IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
                    for (int i = 0; i < pages.length; i++) {
                        if (((FileEditorInput) designerEditor.getEditorInput()).getFile().getProject()
                                .equals(event.getResource())) {
                            IEditorPart editorPart = pages[i].findEditor(designerEditor.getEditorInput());
                            pages[i].closeEditor(editorPart, true);
                        }
                    }
                }
            });
        }
    }

    @Override
    public Object getAdapter(final Class adapter) {
        if (designerEditor.equals(getActiveEditor())) {
            return this.getActiveEditor().getAdapter(adapter);
        }
        /*
         * if (textEditor.equals(getActiveEditor())) { if (adapter == IPropertySheetPage.class) { return null; } return
         * this.getActiveEditor().getAdapter(adapter); }
         */
        return super.getAdapter(adapter);
    }

    /**
     * Will allow to propagate the Delete evenement in the designer.
     */
    public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
        if (this.equals(getSite().getPage().getActiveEditor())) {
            if (selection instanceof StructuredSelection) {
                StructuredSelection structSel = (StructuredSelection) selection;
                if (structSel.getFirstElement() instanceof EditPart) {
                    if (designerEditor.equals(getActiveEditor())) {
                        designerEditor.selectionChanged(getActiveEditor(), selection);

                    }
                }
            }
        }
    }

    protected FileEditorInput createFileEditorInput() {

        IPath codePath = processor.getCodePath();

        if (codePath.isEmpty()) {
            // reinitialize the processor if there was any problem during the initialization.
            // (should not happen)
            try {
                processor.initPath();
            } catch (ProcessorException e) {
                MessageBoxExceptionHandler.process(e);
            }
            codePath = processor.getCodePath();
        }

        IFile codeFile = ResourcesPlugin.getWorkspace().getRoot().getFile(
                processor.getCodeProject().getFullPath().append(codePath));
        if (!codeFile.exists()) {
            // Create empty one
            try {
                codeFile.create(new ByteArrayInputStream("".getBytes()), true, null); //$NON-NLS-1$
            } catch (CoreException e) {
                // Do nothing.
            }
        }
        return new FileEditorInput(codeFile);
    }

    /**
     * Getter for process.
     * 
     * @return the process
     */
    public IProcess getProcess() {
        return this.process;
    }

    public void updateChildrens() {
        // just call the method add protection will update new childrens and
        // keep old ones (keep to delete automatically
        // when closing job)
        JobResourceManager.getInstance().addProtection(designerEditor);
    }

    /**
     * DOC bqian Comment method "selectNode".
     * 
     * @param node
     */
    public void selectNode(INode node) {
        GraphicalViewer viewer = designerEditor.getViewer();
        Object object = viewer.getRootEditPart().getChildren().get(0);
        if (object instanceof ProcessPart) {
            for (EditPart editPart : (List<EditPart>) ((ProcessPart) object).getChildren()) {
                if (editPart instanceof NodePart) {
                    if (((INodeEditPart) editPart).getModel().equals(node)) {
                        viewer.select(editPart);
                    }
                }
            }
        }
    }

    public boolean isJobAlreadyOpened() {
        return foundExistEditor(this.getEditorInput());
    }

    private boolean foundExistEditor(final IEditorInput editorInput) {
        IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (activeWorkbenchWindow != null) {

            WorkbenchPage page = (WorkbenchPage) activeWorkbenchWindow.getActivePage();
            if (page != null) {
                if (editorInput instanceof RepositoryEditorInput) {
                    RepositoryEditorInput curEditorInput = (RepositoryEditorInput) editorInput;

                    IEditorReference[] ref = page.findEditors(curEditorInput, getEditorId(), IWorkbenchPage.MATCH_INPUT);
                    return ref.length > 1;
                }
            }

        }
        return false;
    }

    /**
     * DOC qzhang Comment method "getEditorId".
     * 
     * @return
     */
    public abstract String getEditorId();

    /**
     * Getter for keepPropertyLocked.
     * 
     * @return the keepPropertyLocked
     */
    public boolean isKeepPropertyLocked() {
        return this.keepPropertyLocked;
    }

    /**
     * Sets the keepPropertyLocked.
     * 
     * @param keepPropertyLocked the keepPropertyLocked to set
     */
    public void setKeepPropertyLocked(boolean keepPropertyLocked) {
        this.keepPropertyLocked = keepPropertyLocked;
    }

    /**
     * Getter for codeEditor.
     * 
     * @return the codeEditor
     */
    public TalendJavaEditor getCodeEditor() {
        return (TalendJavaEditor) this.codeEditor;
    }

    public AbstractTalendEditor getTalendEditor() {
        return designerEditor;
    }

    /**
     * The <code>MultiPageEditorPart</code> implementation of this <code>IWorkbenchPart</code> method disposes all
     * nested editors. Subclasses may extend.
     */
    @Override
    public void dispose() {
        getSite().setSelectionProvider(null);
        getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(this);
        setInput(null);

        ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);

        super.dispose();

        if (isKeepPropertyLocked()) {
            return;
        }

        // Unlock the process :
        IRepositoryService service = CorePlugin.getDefault().getRepositoryService();
        IProxyRepositoryFactory repFactory = service.getProxyRepositoryFactory();
        try {
            designerEditor.getProperty().eAdapters().remove(dirtyListener);
            repFactory.unlock(designerEditor.getProperty().getItem());
        } catch (PersistenceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        IRepositoryView viewPart = (IRepositoryView) getSite().getPage().findView(IRepositoryView.VIEW_ID);
        if (viewPart != null) {
            viewPart.refresh();
        }

        processEditorInput.setLoadedProcess(null);

        processEditorInput = null;
        designerEditor = null;
        codeEditor = null;
        processor = null;
        process = null;
        dirtyListener = null;
    }
}
