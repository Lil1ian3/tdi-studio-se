// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.demo.imports;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.Resource;
import org.talend.commons.exception.PersistenceException;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.ITDQItemService;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.language.ECodeLanguage;
import org.talend.core.model.general.Project;
import org.talend.core.model.general.TalendNature;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.User;
import org.talend.core.repository.utils.XmiResourceManager;
import org.talend.core.runtime.CoreRuntimePlugin;
import org.talend.repository.ProjectManager;
import org.talend.repository.model.ResourceModelUtils;
import org.talend.repository.ui.actions.importproject.DemoProjectBean;
import org.talend.repository.ui.actions.importproject.EDemoProjectFileType;
import org.talend.repository.ui.actions.importproject.ImportProjectsUtilities;

/**
 * created by ldong on 2014-4-28 Detailled comment
 * 
 */
public abstract class DemosImportTest {

    protected Project originalProject;

    protected Project tempDemoProject;

    protected DemoProjectBean currentDemo;

    protected IPath processItemPath, connectionItemPath, routineItemPath, contextItemPath, bussniessProcessPath, sqlPatternPath,
            documentionPath, processMrPath, tdqProfilingAnaPath, tdqProfilingRepPath, tdqLibrariesRuleSqlPath,
            tdqLibrariesPatternPath, routesItemPath, routeResourcePath, servicesItemPath, mdmDatacontainerPath, mdmDataModelath,
            mdmResourcePath, mdmRolePath, mdmRoutingrulePath, mdmTransformerV2Path, mdmViewPath, mdmWorkflowPath;;

    protected String demoName;

    public static final String TEMP_FOLDER_SUFFIEX = ".tmp";

    protected void initDemo(String pluginID) {
        List<DemoProjectBean> allDemos = ImportProjectsUtilities.getAllDemoProjects();
        for (DemoProjectBean demo : allDemos) {
            if (pluginID.equals(demo.getPluginId())) {
                currentDemo = demo;
                break;
            }
        }
    }

    protected void initTestPaths() {
        String demoProjectPath = currentDemo.getDemoProjectFilePath();
        IPath path = new Path(demoProjectPath);
        path.lastSegment();
        if (currentDemo.getDemoProjectFileType().equals(EDemoProjectFileType.ARCHIVE)) {
            demoName = path.lastSegment().substring(0, path.lastSegment().lastIndexOf("."));
        } else {
            demoName = path.lastSegment();
        }
        processItemPath = new Path("/process/"); //$NON-NLS-1$
        connectionItemPath = new Path("/metadata/"); //$NON-NLS-1$
        routineItemPath = new Path("/code/");
        contextItemPath = new Path("/context/");
        bussniessProcessPath = new Path("/businessProcess/");
        sqlPatternPath = new Path("/sqlPatterns/");
        documentionPath = new Path("/documentations/");
        processMrPath = new Path("/process_mr/");
        tdqProfilingAnaPath = new Path("/TDQ_Data Profiling/Analyses/");
        tdqProfilingRepPath = new Path("/TDQ_Data Profiling/Reports/");
        tdqLibrariesRuleSqlPath = new Path("/TDQ_Libraries/Rules/SQL");
        tdqLibrariesPatternPath = new Path("/TDQ_Libraries/Patterns");
        routesItemPath = new Path("/routes/");
        routeResourcePath = new Path("/route_resources/");
        servicesItemPath = new Path("/services/");

        mdmDatacontainerPath = new Path("/MDM/datacontainer/");
        mdmDataModelath = new Path("/MDM/datamodel/");
        mdmResourcePath = new Path("/MDM/resource/");
        mdmRolePath = new Path("/MDM/role/");
        mdmRoutingrulePath = new Path("/MDM/routingrule/");
        mdmTransformerV2Path = new Path("/MDM/transformerV2/");
        mdmViewPath = new Path("/MDM/view/");
        mdmWorkflowPath = new Path("/MDM/workflow/");
    }

    protected void initTempPro() throws PersistenceException, CoreException {
        createTempDemoProject(demoName);
        Context ctx = CoreRuntimePlugin.getInstance().getContext();
        RepositoryContext repositoryContext = (RepositoryContext) ctx.getProperty(Context.REPOSITORY_CONTEXT_KEY);
        originalProject = repositoryContext.getProject();
        // initilize the DQ sturcture for the original default project
        initDQStructure();
        repositoryContext.setProject(tempDemoProject);
    }

    protected void initDQStructure() {
        if (GlobalServiceRegister.getDefault().isServiceRegistered(ITDQItemService.class)) {
            ITDQItemService tdqService = (ITDQItemService) GlobalServiceRegister.getDefault().getService(ITDQItemService.class);
            if (tdqService != null) {
                tdqService.createDQStructor();
            }
        }
    }

    protected void createTempDemoProject(String demoName) throws CoreException, PersistenceException {
        Project projectInfor = new Project();
        projectInfor.setLabel(demoName);
        projectInfor.setDescription("no desc");
        projectInfor.setLanguage(ECodeLanguage.JAVA);
        User user = PropertiesFactory.eINSTANCE.createUser();
        user.setLogin("testauto@talend.com");
        projectInfor.setAuthor(user);
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

        String technicalLabel = Project.createTechnicalName(projectInfor.getLabel());
        IProject prj = root.getProject(technicalLabel);

        final IWorkspace workspace = ResourcesPlugin.getWorkspace();

        try {
            IProjectDescription desc = null;
            if (prj.exists()) {
                prj.delete(true, null); // always delete to avoid conflicts between 2 tests
            }
            desc = workspace.newProjectDescription(technicalLabel);
            desc.setNatureIds(new String[] { TalendNature.ID });
            desc.setComment(projectInfor.getDescription());

            prj.create(desc, null);
            prj.open(IResource.DEPTH_INFINITE, null);
            prj.setDefaultCharset("UTF-8", null);
        } catch (CoreException e) {
            throw new PersistenceException(e);
        }

        tempDemoProject = new Project();
        // Fill project object
        tempDemoProject.setLabel(projectInfor.getLabel());
        tempDemoProject.setDescription(projectInfor.getDescription());
        tempDemoProject.setLanguage(projectInfor.getLanguage());
        tempDemoProject.setAuthor(projectInfor.getAuthor());
        tempDemoProject.setLocal(true);
        tempDemoProject.setTechnicalLabel(technicalLabel);
        XmiResourceManager xmiResourceManager = new XmiResourceManager();
        Resource projectResource = xmiResourceManager.createProjectResource(prj);
        projectResource.getContents().add(tempDemoProject.getEmfProject());
        projectResource.getContents().add(tempDemoProject.getAuthor());
        xmiResourceManager.saveResource(projectResource);
    }

    protected void removeTempDemoProject() throws PersistenceException, CoreException {
        ProjectManager.getInstance().getFolders(tempDemoProject.getEmfProject()).clear();
        final IProject project = ResourceModelUtils.getProject(tempDemoProject);
        project.delete(true, null);
    }
}
